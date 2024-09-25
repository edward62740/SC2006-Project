package com.nutriroute.controllers;

import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.utils.ServiceLocator;

public class AuthController {
    static IDataStore<String> dataStore = ServiceLocator.getDB();

    private AuthController() {
    }

    public static UserType login(String username, String password) {
        if(dataStore.getUser(username) != null) {
            if(dataStore.getUser(username).getPassword().equals(password)) {
                return dataStore.getUser(username).getUserType();
            }
        }
        return null;
    }
}
