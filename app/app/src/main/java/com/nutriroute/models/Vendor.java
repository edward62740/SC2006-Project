package com.nutriroute.models;

import com.nutriroute.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Vendor extends GenericUser<String>{
    private List<String> restaurants;
    public Vendor(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.VENDOR);
        restaurants = new ArrayList<>();
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(String restaurant) {
        this.restaurants.add(restaurant);
    }

    public void removeRestaurant(String restaurant) {
        this.restaurants.remove(restaurant);
    }
}
