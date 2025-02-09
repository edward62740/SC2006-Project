package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public abstract class Request <T> {
    private T id;
    private String description;
    private RequestStatus status;
    private RequestType type;
    private String vendorId;
    private String reason;

    public Request(T id, String description, RequestType type) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.status = RequestStatus.PENDING;
    }

    public String getVendorId() {return vendorId;}

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void approve() {
        this.status = RequestStatus.APPROVED;
    }

    public void deny() {
        this.status = RequestStatus.DENIED;
    }

    public void setReason(String reason) {this.reason = reason;}

    public String getReason() {return reason;}
}
