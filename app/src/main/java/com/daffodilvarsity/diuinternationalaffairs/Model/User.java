package com.daffodilvarsity.diuinternationalaffairs.Model;

/**
 * Created by Jibunnisa on 5/8/2017.
 */

public class User{

    private String userId;
    private String name;
    private String usertype;
    private String semesterOrDepartment;
    private String email;
    private String phone;

    public User(String userId, String name, String usertype, String semesterOrDepartment, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.usertype = usertype;
        this.semesterOrDepartment = semesterOrDepartment;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getSemesterOrDepartment() {
        return semesterOrDepartment;
    }

    public void setSemesterOrDepartment(String semesterOrDepartment) {
        this.semesterOrDepartment = semesterOrDepartment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
