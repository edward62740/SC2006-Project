package com.nutriroute.models;

public class Claim {
    private String id;         // Unique identifier for the claim
    private String restaurantId; // ID of the restaurant the claim is associated with
    private String vendorId;   // ID of the vendor making the claim
    private String status;     // Status of the claim (e.g., pending, accepted, rejected)
    private String reason;     // Reason for rejection (if applicable)

    public Claim(String id, String restaurantId, String vendorId, String status) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.vendorId = vendorId;
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
