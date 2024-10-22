package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

import java.time.LocalTime;

public class ClaimRequest extends Request<String> {

//    private String vendorId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantEmail;
    private String restaurantWebsite;
    private String restaurantDescription;
    private LocalTime openHour, closeHour;
    private String proof;
    private String reason;

    public ClaimRequest(String id, String description) {
        super(id, description, RequestType.CLAIM_REQUEST);
        this.setStatus(RequestStatus.PENDING);
    }

    public String getVendorId() {
        //return vendorId;
        return super.getVendorId();
    }

    public void setVendorId(String vendorId) {
        //this.vendorId = vendorId;
        super.setVendorId(vendorId);
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        this.restaurantWebsite = restaurantWebsite;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public LocalTime getOpenHour() {
        return openHour;
    }

    public void setOpenHour(LocalTime openHour) {
        this.openHour = openHour;
    }

    public LocalTime getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(LocalTime closeHour) {
        this.closeHour = closeHour;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getReason() {return reason;}

    public void setReason(String reason){
        this.reason = reason;
    }
}
