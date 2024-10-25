package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public class RestaurantRequest extends Request<String> {

    private String restaurantId;
    private Restaurant newValue;
    private String reason;
    // proof will only be filled if RequestType is Claim. If not Claim, proof will be ""
    private String proof;

    public RestaurantRequest(String id, String description, RequestType requestType) {
        super(id, description, requestType);
        setStatus(RequestStatus.PENDING);
        // To prevent nullException
        if (requestType==RequestType.RESTAURANT_CHANGE_REQUEST)
            setProof("");
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getProof() { return proof;}

    public void setProof(String proof) {this.proof = proof;}

    @Override
    public void deny() {
        super.deny();
        setReason("");
    }

    public void deny(String reason) {
        super.deny();
        setReason(reason);
    }
}
