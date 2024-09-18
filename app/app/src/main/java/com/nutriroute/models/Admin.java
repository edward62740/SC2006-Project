package com.nutriroute.models;

import com.nutriroute.enums.UserType;

public class Admin extends GenericUser<String> {

    public Admin(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.ADMIN);
    }

}
