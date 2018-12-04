package com.example.roland.tgmcathletes;

public class RegisteredUser {


    private String registeredUserName;
    private String registeredEmail;
    private String registeredPassword;



    public RegisteredUser( String registeredUserName, String registeredEmail, String registeredPassword) {

        this.registeredUserName = registeredUserName;
        this.registeredEmail = registeredEmail;
        this.registeredPassword = registeredPassword;
    }



    public String getRegisteredUserName() {
        return registeredUserName;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }
}
