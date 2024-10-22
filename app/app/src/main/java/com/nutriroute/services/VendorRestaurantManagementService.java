package com.nutriroute.services;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IVendorRestaurantManagementService;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class VendorRestaurantManagementService implements IVendorRestaurantManagementService {

    static IDataStore<String> dataStore = ServiceLocator.getDB();

    public List<Restaurant> getRestaurantList(){
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        List<String> restaurantIds = vendor.getRestaurants();

        if (restaurantIds==null)
            return null;
        List<Restaurant> restaurants = new ArrayList<>();
        for (String Id : restaurantIds) {
            restaurants.add(dataStore.getRestaurant(Id));

        }
        return restaurants;
    }

}
