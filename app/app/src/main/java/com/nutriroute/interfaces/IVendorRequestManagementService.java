package com.nutriroute.interfaces;

import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;

public interface IVendorRequestManagementService {
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem);
    public int getNumberOfRequests();
}
