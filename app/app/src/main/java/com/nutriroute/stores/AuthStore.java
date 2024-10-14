package com.nutriroute.stores;

import com.nutriroute.models.GenericUser;

/**
 * Store for the current authenticated user
 */
public class AuthStore {

    private static GenericUser<String> curUser;


    public static GenericUser<String> getCurUser() {
        return curUser;
    }


    public static void setCurUser(GenericUser<String> curUser) {
        AuthStore.curUser = curUser;
    }


    public static boolean isLoggedIn() {
        return curUser != null;
    }
}