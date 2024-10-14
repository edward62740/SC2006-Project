package com.nutriroute.models;

public class RestaurantRequest {
    private String id;
    private String restaurantId;
    private String vendorId;
    private String changeType;
    private Restaurant newValue;
    private String status;
    private String reason;

    public RestaurantRequest() {
        this.status = "";
        this.reason = "";
    }

    public RestaurantRequest(String id, String vendorId, String restaurantId, String changeType, Restaurant newValue, String status){
        this.id = id;
        this.vendorId = vendorId;
        this.restaurantId = restaurantId;
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

    public Restaurant getNewValue() {
        return newValue;
    }

    public void setNewValue(Restaurant newValue) {
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
