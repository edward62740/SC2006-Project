package com.nutriroute.services;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IVendorRequestManagementService;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

public class VendorRequestManagementService implements IVendorRequestManagementService {

    static IDataStore<String> dataStore = ServiceLocator.getDB();

    @Override
    public MenuRequest generateEditMenuItemRequest(Menu menu, int position, MenuItem menuItem) {
        Vendor vendor = (Vendor) AuthStore.getCurUser();
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setId(menu.getId() + "_MenuItemRequest" + getNumberOfRequests());
        menuRequest.setMenuItemId(String.valueOf(position));
        menuRequest.setRestaurantId(menu.getId());
        menuRequest.setVendorId(vendor.getId());
        menuRequest.setChangeType("update");
        menuRequest.setNewValue(menuItem);
        return new MenuRequest();
    }

    @Override
    public int getNumberOfRequests() {
        return 0;
    }
}
