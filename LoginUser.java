package com.example.roland.tgmcathletes;

public class LoginUser {

    private String loginId;
    private String loginUserName;
    private String loginPassword;

    public LoginUser() {

    }

    public LoginUser(String loginId, String loginUserName, String loginPassword) {
        this.loginId = loginId;
        this.loginUserName = loginUserName;
        this.loginPassword = loginPassword;
    }



    public String getLoginId() {
        return loginId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }
}
