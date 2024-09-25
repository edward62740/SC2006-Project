package com.nutriroute.models;

import com.nutriroute.enums.UserType;

public abstract class GenericUser <T> {
    private String name;
    private String email;
    private String password;
    private T id;
    private UserType userType;

    public GenericUser(String name, String email, String password, T id, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


}
