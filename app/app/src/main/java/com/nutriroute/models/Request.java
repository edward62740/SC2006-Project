package com.nutriroute.models;

public abstract class Request <T> {
    private T id;
    private String title;
    private String description;
    private Integer status;

    public Request(String title, String description, T id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = 0;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void approve() {
        this.status = 1;
    }

    public void deny() {
        this.status = 2;
    }
}
