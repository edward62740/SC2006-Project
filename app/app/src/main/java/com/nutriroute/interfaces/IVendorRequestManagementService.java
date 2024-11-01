package com.nutriroute.interfaces;

import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

import java.util.List;

public interface IVendorRequestManagementService {
    public MenuRequest generateAddMenuItemRequest(Restaurant restaurant, Menu menu, MenuItem menuItem);
    public MenuRequest generateEditMenuItemRequest(Restaurant restaurant, Menu menu, int position, MenuItem menuItem);
    public RestaurantRequest generateEditRestaurantRequest(Restaurant restaurant, Restaurant newValue);
    public RestaurantRequest generateRestaurantClaimRequest(Restaurant restaurant, String proof);
    public int getNumberOfRequests();
    public List<Request<String>> getRequests();
    public void fillMenuItemOldValues(MenuItem oldItem, MenuItem newItem);
    public void fillRestaurantOldValues(Restaurant oldRestaurant, Restaurant newRestaurant);
}
