package com.nutriroute.models;

import android.util.Pair;

import com.nutriroute.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class User extends GenericUser<String> {

    private int height;
    private int weight;
    private int targetCalories;
    private int targetWeight;
    private int todayCalories;
    private boolean isPrescribed;
    // array of calories consumed each day
    private List<CalorieDay> caloriesHistory;

    // just assume that the food history will be NULL for the days that are skipped



    public User(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.USER);
        caloriesHistory = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getTodayCalories() {
        return todayCalories;
    }

    public void setTodayCalories(int todayCalories) {
        this.todayCalories = todayCalories;
    }

    public List<CalorieDay> getCaloriesHistory() {
        return caloriesHistory;
    }

    public void addCaloriesHistory(CalorieDay calorieDay) {
        caloriesHistory.add(calorieDay);
    }


}
