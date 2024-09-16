package com.nutriroute.models;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String category;
    private String image; // path to img (do we want this?)

    public MenuItem(String name, String description, double price, String category, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
