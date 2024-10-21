package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public class RestaurantRequest extends Request<String> {

    private String restaurantId;
    private String vendorId;
    private String changeType;
    private Restaurant restaurant;
    private String reason;

    public RestaurantRequest(String id, String description) {
        super(id, description, RequestType.CLAIM_REQUEST);
        this.setStatus(RequestStatus.PENDING);
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant newValue) {
        this.restaurant = newValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
