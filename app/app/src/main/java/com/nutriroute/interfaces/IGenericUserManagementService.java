package com.nutriroute.interfaces;

import com.nutriroute.enums.UserType;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;

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
