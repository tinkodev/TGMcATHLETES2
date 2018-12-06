package com.example.thewskilla.tgmapp.Login_SignUp_Screen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thewskilla.tgmapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail,editTextUsername, editTextPassword1,editTextPassword2;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        /** Full Screen **/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /** Inicializalas**/
        editTextEmail = (EditText) findViewById(R.id.SignUp_email_id);
        editTextUsername = (EditText) findViewById(R.id.SignUp_username_id);
        editTextPassword1 = (EditText) findViewById(R.id.SignUp_password_id);
        editTextPassword2 = (EditText) findViewById(R.id.SignUp_password_id2);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.SignUp_button_id).setOnClickListener(this);


        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            //handle the already login user
        }
    }

    public void registerUser(){

        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();

        /** Validate **/

        if(email.isEmpty()){
            editTextEmail.setError("E-mail is required");
            editTextEmail.requestFocus();
            return;
        }
        if(username.isEmpty()){
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid E-mail");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword1.setError("Password is required");
            editTextPassword1.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword1.setError("Minimum length of password should be 6");
            editTextPassword1.requestFocus();
            return;
        }
        if(password2.isEmpty()){
            editTextPassword2.setError("Password is required");
            editTextPassword2.requestFocus();
            return;
        }

        if(password2.length() < 6){
            editTextPassword2.setError("Minimum length of password should be 6");
            editTextPassword2.requestFocus();
            return;
        }
        if(!password.equals(password2)){
              Toast.makeText(getApplicationContext(),"Passwords doesn't match", Toast.LENGTH_SHORT).show();
              return;

        }



        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();
                    User user = new User(email,username);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                        }
                    });
                    Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else{
                    //Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignUp_button_id:
                        registerUser(); /**Methodus hivas**/
                break;


        }
    }
}
