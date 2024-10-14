package com.nutriroute.controllers;

import android.util.Pair;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IVendorRequestManagementService;
import com.nutriroute.interfaces.IVendorRestaurantManagementService;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Restaurant;
import com.nutriroute.services.VendorRequestManagementService;
import com.nutriroute.services.VendorRestaurantManagementService;
import com.nutriroute.utils.ServiceLocator;

import java.sql.Time;
import java.util.List;

public class VendorController {
    private static final IVendorRestaurantManagementService vendorRestaurantMS = new VendorRestaurantManagementService();
    private static final IVendorRequestManagementService vendorRequestMS = new VendorRequestManagementService();

    private static final IDataStore<String> dataStore = ServiceLocator.getDB();

    private VendorController(){
    }

    public static List<Restaurant> getRestaurantList(){
        return vendorRestaurantMS.getRestaurantList();
    }

    public static MenuRequest generateNewMenuRequest(Menu menu, int position, MenuItem menuItem){
        return vendorRequestMS.generateEditMenuItemRequest(menu, position, menuItem);
    }

    // public static Pair<Time, Time>

    public static void logoutAndExit() {
        AuthController.logout();
    }
}
