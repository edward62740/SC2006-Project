package com.nutriroute.services;

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

public class VendorRequestManagementService implements IVendorRequestManagementService {

    static IDataStore<String> dataStore = ServiceLocator.getDB();

    @Override
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        MenuRequest menuRequest = new MenuRequest(menu.getId() + "_MenuItemRequest", "Edit Menu Item Request");
        menuRequest.setMenuItemId(String.valueOf(position));
        menuRequest.setRestaurantId(menu.getId());
        menuRequest.setVendorId(vendor.getId());
        menuRequest.setChangeType("update");
        menuRequest.setNewValue(menuItem);
        System.out.println("Menu Request generated: " + menuRequest);
        return menuRequest;
    }

    public RestaurantRequest generateRestaurantClaimRequest(Restaurant restaurant) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        RestaurantRequest restaurantRequest = new RestaurantRequest(restaurant.getId() + "_ClaimRequest", "Claim restaurant Request");
        restaurantRequest.setVendorId(vendor.getId());
        restaurantRequest.setChangeType("add");
        restaurantRequest.setRestaurant(restaurant);
        System.out.println("Restaurant Request generated: " + restaurantRequest);
        return restaurantRequest;
    }

    @Override
    public int getNumberOfRequests() {
        return 0;
    } //removed use because this param cannot be synchronized
}
