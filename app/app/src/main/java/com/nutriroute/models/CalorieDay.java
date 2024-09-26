package com.nutriroute.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// class to store the calorie history of a single "day" for a User
public class CalorieDay {

    private LocalDate date;
    private List<Integer> caloriesConsumed;
    private List<String> foodConsumed; // this is MENUITEMS ID
    private List<String> foodRestaurant; // this is RESTAURANT ID
    // note that the relative ordering of the attrs in the lists should be maintained

    public CalorieDay(LocalDate date) {
        this.date = date;
        caloriesConsumed = new ArrayList<>();
        foodConsumed = new ArrayList<>();
        foodRestaurant = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Integer> getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public List<String> getFoodConsumed() {
        return foodConsumed;
    }

    public List<String> getFoodRestaurant() {
        return foodRestaurant;
    }

    public void addCaloriesConsumed(int calories) {
        caloriesConsumed.add(calories);
    }

    public void addFoodConsumed(String foodId) {
        foodConsumed.add(foodId);
    }

    public void addFoodRestaurant(String restaurantId) {
        foodRestaurant.add(restaurantId);
    }

    public int getTotalCalories() {
        int total = 0;
        if (caloriesConsumed == null) {
            return total;
        }
        for (int calories : caloriesConsumed) {
            total += calories;
        }
        return total;
    }


}
