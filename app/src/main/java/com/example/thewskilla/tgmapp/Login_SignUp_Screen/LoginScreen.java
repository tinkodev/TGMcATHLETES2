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

import com.example.thewskilla.tgmapp.Home_Screen.MainActivity;
import com.example.thewskilla.tgmapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        /** Full Screen **/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //TextView megkeresese id-ja alapjan
        findViewById(R.id.login_textView_id).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance(); /** Adatbazis inicializalasa**/

        findViewById(R.id.login_button_id).setOnClickListener(this);

        /** Inicializalas**/
        editTextEmail = (EditText) findViewById(R.id.login_email_id);
        editTextPassword = (EditText) findViewById(R.id.login_password_id);

        progressBar = (ProgressBar) findViewById(R.id.progressbar); /** Progress bar**/

    }

    public void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        /** Validate **/

        if(email.isEmpty()){
            editTextEmail.setError("E-mail is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid E-mail");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE); /** Megjelenik a progress bar**/


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE); /** Eltunik a progress bar**/
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); /** Ne vigyen vissza az azelotti activitybe**/
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_textView_id: /** azonositjuk az id alapjan **/

                startActivity(new Intent(this, SignUpScreen.class)); /** eliditjuk a SignUpScreent**/

                break;
            case R.id.login_button_id:
                userLogin();
                break;
        }
    }
}
