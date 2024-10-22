package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public class RestaurantRequest extends Request<String> {

    private String restaurantId;
//    private String vendorId;
    private String changeType;
    private Restaurant newValue;
    private String reason;

    public RestaurantRequest(String id, String description) {
        super(id, description, RequestType.RESTAURANT_CHANGE_REQUEST);
        this.setStatus(RequestStatus.PENDING);
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getVendorId() {
        //return vendorId;
        return super.getVendorId();
    }

    public void setVendorId(String vendorId) {
        //this.vendorId = vendorId;
        super.setVendorId(vendorId);
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
