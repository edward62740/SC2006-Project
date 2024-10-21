package com.nutriroute.interfaces;

import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

public interface IVendorRequestManagementService {
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem);
    RestaurantRequest generateRestaurantClaimRequest(Restaurant restaurant);
    public int getNumberOfRequests();
}
