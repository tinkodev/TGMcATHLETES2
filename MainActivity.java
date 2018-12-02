package com.example.roland.tgmcathletes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity  {

      private EditText userName,password;
      private  Button register,login;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("tgm-cathletes");

        userName=(EditText)findViewById(R.id.userNameLog);
        password=(EditText)findViewById(R.id.passwordLog) ;
        register = (Button) findViewById(R.id.buttonRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(v.getContext(), Activity_register.class);
               // v.getContext().startActivity(intent);
                addLogin();

            }

        });

/*
        login = (Button) findViewById(R.id.buttonLogin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addLogin();
                }

        });*/

    }


    public void addLogin(){
        String loginUser = userName.getText().toString().trim();
        String loginPassword = password.getText().toString().trim();

        if(!TextUtils.isEmpty(loginUser)&&!TextUtils.isEmpty(loginPassword)){

            String id = databaseReference.push().getKey();
            LoginUser login = new LoginUser(id,loginUser,loginPassword);

            databaseReference.child(id).setValue(login);
            Toast.makeText(this,"Name,password added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Please type the UserName or Email", Toast.LENGTH_LONG).show();
        }

    }




}
