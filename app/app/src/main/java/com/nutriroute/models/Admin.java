package com.nutriroute.models;

public class Admin extends GenericUser<Double> {

    public Admin(String name, String email, String password, Double id) {
        super(name, email, password, id);
    }

}
