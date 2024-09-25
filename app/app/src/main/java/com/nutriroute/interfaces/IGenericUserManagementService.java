package com.nutriroute.interfaces;

import com.nutriroute.enums.UserType;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;

public interface IGenericUserManagementService {

    void registerGenericUser(String name, String password, String email, UserType userType);

    void modifyGenericUser(GenericUser<String> user);

}
