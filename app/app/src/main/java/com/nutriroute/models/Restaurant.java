package com.nutriroute.models;


import java.time.LocalTime;

/**
 * This class represents a restaurant.
 *
 * Note: It is highly recommended at runtime, before creating a particular Restaurant instance,
 * to lookup the postal code and populate the location field with the GPS coordinates.
 * <p>
 * Evidently, if the Vendor creates a new restaurant, there should be a valid location.
 */
public class Restaurant {
    private Menu menu;
    private String name;
    private String address; // this should be the postal code
    private String phone;
    private String email;
    private String website;
    private String description;
    private String image; // path to img (do we want this?)
    private String id;
    private String location; // this should be the GPS coordinates
    private LocalTime openHour;
    private LocalTime closeHour;

    public Restaurant(Menu menu, String name, String address, String phone, String email, String website, String description, String image, String id, LocalTime openHour, LocalTime closeHour) {
        this.menu = menu;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.description = description;
        this.image = image;
        this.id = id;
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public Restaurant() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour){
        this.openHour = LocalTime.parse(openHour);
    }

    public LocalTime getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour){
        this.closeHour = LocalTime.parse(closeHour);
    }
}
