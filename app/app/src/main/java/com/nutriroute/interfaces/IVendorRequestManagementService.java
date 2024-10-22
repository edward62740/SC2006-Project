package com.nutriroute.interfaces;

import com.nutriroute.models.ClaimRequest;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

import java.util.List;

public interface IVendorRequestManagementService {
    public MenuRequest generateAddMenuItemRequest(Menu menu, MenuItem menuItem);
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem);
    public RestaurantRequest generateEditRestaurantRequest(Restaurant restaurant, Restaurant newValue);
    public ClaimRequest generateRestaurantClaimRequest(Restaurant restaurant, String proof);
    public int getNumberOfRequests();
    public List<Request<String>> getRequests();
}
