package com.nutriroute.models;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;

public abstract class Request <T> {
    private T id;
    private String description;
    private RequestStatus status;
    private RequestType type;

    public Request(T id, String description, RequestType type) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.status = RequestStatus.PENDING;
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
}
