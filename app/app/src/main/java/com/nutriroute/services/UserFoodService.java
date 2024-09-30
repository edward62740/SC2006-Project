package com.nutriroute.services;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IUserFoodService;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserFoodService implements IUserFoodService {
    User user = (User) AuthStore.getCurUser(); // unsafe cast to User since it must be User by now
    static IDataStore<String> dataStore = ServiceLocator.getDB();

    @Override
    public List<Restaurant> queryRestaurants(Comparator<Restaurant> sortingFunction) {
        // Get the collection of restaurants from dataStore
        Collection<Restaurant> restaurantCollection = dataStore.getRestaurants();

        // Convert the collection to a List
        List<Restaurant> restaurantList = new ArrayList<>(restaurantCollection);


        if (sortingFunction != null) {
            restaurantList.sort(sortingFunction);
        }

        return restaurantList;
    }

    @Override
    public Comparator<Restaurant> sortByDistance() {
        return null;
    }
}
