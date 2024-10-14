package com.nutriroute.models;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String category;
    private String image; // path to img (do we want this?)
    private String id;
    private int calories;

    public MenuItem() {}

    public MenuItem(String name, String description, double price, String category, String image, int calories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    @Override
    public boolean equals(Object o){
        if (o==this) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem c = (MenuItem) o;

        return (name.equals(c.name) &&
                description.equals(c.description) &&
                Double.compare(price, c.price)==0 &&
                category.equals(c.category) &&
                image.equals(c.image) &&
                calories==c.calories
                );
    }
}
