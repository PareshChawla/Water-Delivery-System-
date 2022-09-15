package com.example.notworking;

public class User {

    private String userName;
    private String password;
    private String email;
    private String mobileNo;
    private String area;
    private String flatNo;
    private String confirmPass;
    private int usertype;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public User() {

    }

    public User(String userName, String password, String email, String mobileNo, String area, String flatNo, String confirmPass, int usertype) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.area = area;
        this.flatNo = flatNo;
        this.confirmPass = confirmPass;
        this.usertype = usertype;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }







//    public User (String userName, String password, String email, String mobileNo, String area, String flatNo, String confirmPass), int usertype {
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.mobileNo = mobileNo;
//        this.area = area;
//        this.flatNo = flatNo;
//        this.confirmPass = confirmPass;
//        this.usertype = usertype;
//    }
}
