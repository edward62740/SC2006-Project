package com.nutriroute.interfaces;

import com.nutriroute.enums.UserType;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;

/**
 * This is the interface to create a new generic user, or modify them.
 * It is used to abstract the user management over IDataStore.
 */
public interface IGenericUserManagementService {

    /*
     * Register a new user
     */
    void registerGenericUser(String name, String password, String email, UserType userType);

    /*
     * Modify a user
     */
    void modifyGenericUser(GenericUser<String> user);

}
