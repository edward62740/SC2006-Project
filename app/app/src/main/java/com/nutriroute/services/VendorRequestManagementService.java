package com.nutriroute.services;

import com.nutriroute.enums.RequestType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IVendorRequestManagementService;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendorRequestManagementService implements IVendorRequestManagementService {

    static IDataStore<String> dataStore = ServiceLocator.getDB();

    @Override
    public MenuRequest generateAddMenuItemRequest(Menu menu, MenuItem menuItem) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        int count=1;
        for (Request<String> request : dataStore.getRequests()){
            if (request.getType()==RequestType.MENU_CHANGE_REQUEST){
                MenuRequest menuRequest = (MenuRequest) request;
                if (menuRequest.getRestaurantId().equals(menu.getId())){
                    count++;
                }
            }
        }
        MenuRequest menuRequest = new MenuRequest(menu.getId() + "_MenuItemRequest_" + count, "Add Menu Item Request");
        menuRequest.setMenuItemId("0");
        menuRequest.setRestaurantId(menu.getId());
        menuRequest.setVendorId(vendor.getId());
        menuRequest.setChangeType("add");
        menuRequest.setNewValue(menuItem);
        System.out.println("Menu Request generated: " + menuRequest);
        return menuRequest;
    }

    @Override
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        int count=1;
        for (Request<String> request : dataStore.getRequests()){
            if (request.getType()==RequestType.MENU_CHANGE_REQUEST){
                MenuRequest menuRequest = (MenuRequest) request;
                if (menuRequest.getRestaurantId().equals(menu.getId())){
                    count++;
                }
            }
        }
        MenuRequest menuRequest = new MenuRequest(menu.getId() + "_MenuItemRequest_" + count, "Edit Menu Item Request");
        menuRequest.setMenuItemId(String.valueOf(position));
        menuRequest.setRestaurantId(menu.getId());
        menuRequest.setVendorId(vendor.getId());
        menuRequest.setChangeType("update");
        fillMenuItemOldValues(menu.get(position), menuItem);
        menuRequest.setNewValue(menuItem);
        System.out.println("Menu Request generated: " + menuRequest);
        return menuRequest;
    }

    public RestaurantRequest generateRestaurantClaimRequest(Restaurant restaurant, String proof) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        int count=1;
        for (Request<String> request : dataStore.getRequests()){
            if (request.getType()==RequestType.CLAIM_REQUEST){
                RestaurantRequest claimRequest = (RestaurantRequest) request;
                if (claimRequest.getVendorId().equals(vendor.getId())){
                    count++;
                }
            }
        }

        RestaurantRequest claimRequest = new RestaurantRequest(vendor.getId() + "_ClaimRequest_" + count, "Claim restaurant Request", RequestType.CLAIM_REQUEST);
        claimRequest.setVendorId(vendor.getId());
        claimRequest.setRestaurantId(restaurant.getName());
        claimRequest.setNewValue(restaurant);
        claimRequest.setProof(proof);
        System.out.println("Restaurant Request generated: " + claimRequest);
        return claimRequest;
    }

    @Override
    public RestaurantRequest generateEditRestaurantRequest(Restaurant restaurant, Restaurant newValue) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        int count=1;
        for (Request<String> request : dataStore.getRequests()){
            if (request.getType()==RequestType.RESTAURANT_CHANGE_REQUEST){
                RestaurantRequest restaurantRequest = (RestaurantRequest) request;
                if (restaurantRequest.getRestaurantId().equals(restaurant.getId())){
                    count++;
                }
            }
        }
        RestaurantRequest restaurantRequest = new RestaurantRequest(restaurant.getId() + "_RestaurantRequest_" + count, "Edit Restaurant Request", RequestType.RESTAURANT_CHANGE_REQUEST);
        restaurantRequest.setRestaurantId(restaurant.getId());
        restaurantRequest.setVendorId(vendor.getId());
        restaurantRequest.setNewValue(newValue);
        System.out.println("Menu Request generated: " + restaurantRequest);
        return restaurantRequest;
    }

    @Override
    public List<Request<String>> getRequests() {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        List<Request<String>> requests = new ArrayList<>();
        for (Request<String> request : dataStore.getRequests()){
            if (request.getVendorId().equals(vendor.getId())){
                requests.add(request);
            }
        }
        return requests;
    }

    @Override
    public int getNumberOfRequests() {
        return dataStore.getRequests().size();
    } //removed use because this param cannot be synchronized

    @Override
    public void fillMenuItemOldValues(MenuItem oldItem, MenuItem newItem) {
        if (newItem.getName()==null) {
            newItem.setName(oldItem.getName());
        }
        if (newItem.getDescription()==null) {
            newItem.setDescription(oldItem.getDescription());
        }
        if (newItem.getPrice()==-1) {
            newItem.setPrice(oldItem.getPrice());
        }
        if (newItem.getCalories()==-1) {
            newItem.setCalories(oldItem.getCalories());
        }
    }

    @Override
    public void fillRestaurantOldValues(Restaurant oldRestaurant, Restaurant newRestaurant) {

    }
}
