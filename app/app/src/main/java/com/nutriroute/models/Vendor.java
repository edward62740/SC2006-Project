package com.nutriroute.models;

import com.nutriroute.enums.UserType;

import java.util.List;

public class Vendor extends GenericUser<String>{
    private List<Restaurant> restaurants;
    public Vendor(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.VENDOR);
    }
}
