package com.example.roland.tgmcathletes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_register extends AppCompatActivity {

    private EditText userName, email, password1,password2;
    private  Button registerReg;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        databaseReference = FirebaseDatabase.getInstance().getReference("tgm-cathletes");

        userName=(EditText)findViewById(R.id.userNameReg);
        email=(EditText)findViewById(R.id.emailReg);
        password1=(EditText)findViewById(R.id.passwordReg) ;
        password2=(EditText)findViewById(R.id.passwordReg2);

        registerReg = (Button) findViewById(R.id.buttonReg);
        registerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
                addRegisteredUser();

            }

        });

    }
    public void addRegisteredUser(){
        String registeredUser=userName.getText().toString();
        String registeredEmail=email.getText().toString();
        String registeredPassword1=password1.getText().toString();
        String registeredPassword2=password2.getText().toString();

        if(!TextUtils.isEmpty(registeredUser)&&!TextUtils.isEmpty(registeredEmail)&&!TextUtils.isEmpty(registeredPassword1)&&!TextUtils.isEmpty(registeredPassword2)){

            String id=databaseReference.push().getKey();
            RegisteredUser registered = new RegisteredUser(id,registeredUser,registeredEmail,registeredPassword1);

            databaseReference.child(id).setValue(registered);
            userName.setText("");
            email.setText("");
            password1.setText("");
        }
        else{
            Toast.makeText(Activity_register.this,"Please type the UserName,Email or Password", Toast.LENGTH_LONG).show();
        }
    }
}
