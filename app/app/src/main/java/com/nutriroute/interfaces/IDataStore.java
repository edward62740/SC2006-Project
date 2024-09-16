package com.nutriroute.interfaces;

import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;

public interface IDataStore {


    public GenericUser<?> getUser(Double id);

    public boolean addUser(GenericUser<?> user);

    public boolean updateUser(GenericUser<?> user);

    public boolean deleteUser(GenericUser<?> id);


}
