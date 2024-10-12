package com.nutriroute.models;

public class MenuRequest {
    private String id;
    private String menuItemId;
    private String restaurantId;
    private String vendorId;
    private String changeType;
    private MenuItem newValue;
    private String status;
    private String reason;

    public MenuRequest() {
        id = "";
        menuItemId = "";
        restaurantId = "";
        vendorId = "";
        changeType = "";
        status = "";
        reason = "";
    }

    public MenuRequest(String id, String menuItemId, String vendorId, String changeType, MenuItem newValue, String status, String restaurantId){
        this.id = id;
        this.menuItemId = menuItemId;
        this.vendorId = vendorId;
        this.changeType = changeType;
        this.newValue = newValue;
        this.status = status;
        this.restaurantId = restaurantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
