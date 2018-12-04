package com.example.roland.tgmcathletes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_register extends AppCompatActivity {

    private EditText userName, email, password1,password2;
    private  Button registerReg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mAuth = FirebaseAuth.getInstance();

        userName=(EditText)findViewById(R.id.userNameReg);
        email=(EditText)findViewById(R.id.emailReg);
        password1=(EditText)findViewById(R.id.passwordReg) ;
        password2=(EditText)findViewById(R.id.passwordReg2);

        registerReg = (Button) findViewById(R.id.buttonReg);


    }
    public void addRegisteredUser(){
        String registeredUser=userName.getText().toString().trim();
        String registeredEmail=email.getText().toString().trim();
        String registeredPassword1=password1.getText().toString().trim();
        String registeredPassword2=password2.getText().toString().trim();

        if(registeredEmail.isEmpty()){
            email.setError("E-mail is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(registeredEmail).matches()){
            email.setError("Please enter valid E-mail");
            email.requestFocus();
        }

        if(registeredPassword1.isEmpty()){
            password1.setError("Password is required");
            password1.requestFocus();
            return;
        }

        if(registeredPassword1.length() < 6){
            password1.setError("Minimum length of password should be 6");
            password1.requestFocus();
            return;
        }

        if(registeredPassword2.isEmpty()){
            password2.setError("Password is required");
            password2.requestFocus();
            return;
        }

        if(registeredPassword2.length() < 6){
            password2.setError("Minimum length of password should be 6");
            password2.requestFocus();
            return;
        }

        if(registeredUser.isEmpty()){
            userName.setError("UserName is required");
            userName.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(registeredEmail, registeredPassword1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Activity_register.this, MainActivity.class);
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

    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonReg:
                addRegisteredUser();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.buttonLogin:

                break;
        }
    }
}
