package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public class MenuRequest extends Request<String> {
    private String menuItemId;
    private String restaurantId;
//    private String vendorId;
    private String changeType;
    private MenuItem newValue;
    private String reason;

    public MenuRequest(String id, String description) {
        super(id, description, RequestType.MENU_CHANGE_REQUEST);
        this.setStatus(RequestStatus.PENDING);
    }


    public String getMenuItemID() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getVendorId() {
        return super.getVendorId();
    }

    public void setVendorId(String vendorId) {
        super.setVendorId(vendorId);
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public MenuItem getNewValue() {
        return newValue;
    }

    public void setNewValue(MenuItem newValue) {
        this.newValue = newValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
