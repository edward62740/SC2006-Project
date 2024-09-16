package com.nutriroute.models;

public class Vendor extends GenericUser<Double>{
    public Vendor(String name, String email, String password, Double id) {
        super(name, email, password, id);
    }
}
