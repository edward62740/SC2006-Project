package com.nutriroute.interfaces;

import com.nutriroute.models.User;
import com.nutriroute.stores.ServiceLocator;

public interface IUserManagementService {
    IDataStore<String> dataStore = ServiceLocator.getDB();

    void registerUser(String username, String password, String email, String userType);

    void modifyUser(User user);

}
