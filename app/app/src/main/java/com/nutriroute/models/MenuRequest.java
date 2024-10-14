package com.nutriroute.models;

public class MenuRequest {
    private String id;           // Unique identifier for the menu request
    private String menuItemId;   // ID of the menu item being requested for change
    private String vendorId;      // ID of the vendor making the request
    private String changeType;    // Type of change (e.g., add, update, remove)
    private String newValue;      // New value for the menu item (if applicable)
    private String status;        // Status of the menu request (e.g., pending, accepted, rejected)
    private String reason;        // Reason for rejection (if applicable)

    public MenuRequest(String id, String menuItemId, String vendorId, String changeType, String newValue, String status) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.vendorId = vendorId;
        this.changeType = changeType;
        this.newValue = newValue;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
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

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {

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
