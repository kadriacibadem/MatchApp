package com.example.macthapp;

public class User {
    String email,name,age,userId,userProfile;



    public User(){

    }

    public User(String email, String name, String age, String userId, String userProfile) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.userId = userId;
        this.userProfile=userProfile;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }


}
