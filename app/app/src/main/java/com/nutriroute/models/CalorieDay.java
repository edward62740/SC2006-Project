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
        caloriesConsumed = new ArrayList<>(3);
        foodConsumed = new ArrayList<>(3);
        foodRestaurant = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            caloriesConsumed.add(0);
            foodConsumed.add("");
            foodRestaurant.add("");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Integer> getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void initCaloriesConsumed() {
        caloriesConsumed = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            caloriesConsumed.add(0);
        }
    }

    public List<String> getFoodConsumed() {
        return foodConsumed;
    }

    public void initFoodConsumed() {
        foodConsumed = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            foodConsumed.add("");
        }
    }

    public List<String> getFoodRestaurant() {
        return foodRestaurant;
    }

    public void initFoodRestaurant() {
        foodRestaurant = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            foodRestaurant.add("");
        }
    }


    public void addCaloriesConsumed(int calories, int index) {
        if (index < caloriesConsumed.size()) { //caloriesConsumed.size() == 3 at init
            caloriesConsumed.set(index, calories);
        }
        else {
            caloriesConsumed.add(calories);
        }
    }

    public void addFoodConsumed(String foodId, int index) {
        if (index < foodConsumed.size()) { //foodConsumed.size() == 3 at init
            foodConsumed.set(index, foodId);
        }
        else {
            foodConsumed.add(foodId);
        }
    }

    public void addFoodRestaurant(String restaurantId, int index) {
        if (index < foodRestaurant.size()) { //foodRestaurant.size() == 3 at init
            foodRestaurant.set(index, restaurantId);
        }
        else {
            foodRestaurant.add(restaurantId);
        }

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
