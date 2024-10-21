package com.nutriroute.controllers;

import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

/**
 * This is the authentication controller which is responsible for handling the the logic for the authentication.
 * The distinction between the controller and the activities/fragments is that the controller
 * is the one that deals with the service layer.
 */
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

    public static UserType googleLogin(String email) {
        if(dataStore.getUser(email) != null) {
                return dataStore.getUser(email).getUserType();
        }
        return null;
    }

    public static void logout() {
        AuthStore.setCurUser(null);
    }

}
