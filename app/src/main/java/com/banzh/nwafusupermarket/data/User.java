package com.banzh.nwafusupermarket.data;

public class User {

    private String userName;
    private String password;
    private String userSignal;
    private String userToken;

    public User(String userName, String password, String userToken) {
        this.userName = userName;
        this.password = password;
        this.userToken = userToken;
        this.userSignal = "快更换签名吧...";
    }

    public User(String userName, String password, String userSignal, String userToken) {
        this.userName = userName;
        this.password = password;
        this.userSignal = userSignal;
        this.userToken = userToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserSignal() {
        return userSignal;
    }

    public void setUserSignal(String userSignal) {
        this.userSignal = userSignal;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
