package com.nutriroute.controllers;

import android.util.Pair;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IRequestMailboxService;
import com.nutriroute.interfaces.IVendorRequestManagementService;
import com.nutriroute.interfaces.IVendorRestaurantManagementService;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.services.RequestMailboxService;
import com.nutriroute.services.VendorRequestManagementService;
import com.nutriroute.services.VendorRestaurantManagementService;
import com.nutriroute.utils.ServiceLocator;

import java.sql.Time;
import java.util.List;

public class VendorController {
    private static final IVendorRestaurantManagementService vendorRestaurantMS = new VendorRestaurantManagementService();
    private static final IVendorRequestManagementService vendorRequestMS = new VendorRequestManagementService();
    private static final IRequestMailboxService requestMailboxService = new RequestMailboxService();

    private static final IDataStore<String> dataStore = ServiceLocator.getDB();

    private VendorController(){
        requestMailboxService.synchronize();
    }

    public static List<Restaurant> getRestaurantList(){
        return vendorRestaurantMS.getRestaurantList();
    }

    public static int getNumberOfRequests(){
        return vendorRequestMS.getNumberOfRequests();
    }

    public static List<Request<String>> getRequests(){
        return vendorRequestMS.getRequests();
    }

    public static void generateNewMenuRequest(Menu menu, MenuItem menuItem){
        Request<String> req = vendorRequestMS.generateAddMenuItemRequest(menu, menuItem);
        requestMailboxService.send(req);
    }

    public static void generateNewMenuRequest(Menu menu, int position, MenuItem menuItem){

        // modified this to send to requestMailboxService;so the controller doesnt return model class to view
        Request<String> req = vendorRequestMS.generateEditMenuItemRequest(menu, position, menuItem);
        requestMailboxService.send(req);
    }

    public static void generateNewRestaurantRequest(Restaurant restaurant, Restaurant newValue){
        Request<String> req = vendorRequestMS.generateEditRestaurantRequest(restaurant, newValue);
        requestMailboxService.send(req);
    }

    public static void generateNewRestaurantClaimRequest(Restaurant restaurant, String proof){
        Request<String> req = vendorRequestMS.generateRestaurantClaimRequest(restaurant, proof);
        requestMailboxService.send(req);
    }

    public static void logoutAndExit() {
        AuthController.logout();
    }
}
