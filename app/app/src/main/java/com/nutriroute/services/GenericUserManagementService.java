package com.nutriroute.services;

import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IGenericUserManagementService;
import com.nutriroute.models.GenericUser;
import com.nutriroute.utils.GenericUserFactory;
import com.nutriroute.utils.ServiceLocator;

public class GenericUserManagementService implements IGenericUserManagementService {
    IDataStore<String> dataStore = ServiceLocator.getDB();


    public void registerGenericUser(String name, String password, String email, UserType userType) {
        GenericUser<String> user = GenericUserFactory.createUser(userType);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setId(name); // for now the id is the name
        dataStore.setUser(user, user.getId());
    }

    public void modifyGenericUser(GenericUser<String> user) {
        if (user != null && dataStore.getUser(user.getId()) != null) {
            dataStore.setUser(user, user.getId());
        }
    }
}
