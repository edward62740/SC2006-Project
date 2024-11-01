package com.nutriroute.models;

import android.util.Pair;

import com.nutriroute.enums.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends GenericUser<String> {

    private int height;
    private int weight;
    private int targetCalories;
    private int targetWeight;
    private boolean isPrescribed;
    // array of calories consumed each day
    private List<CalorieDay> caloriesHistory;
    private CalorieDay caloriesToday;

    public User(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.USER);
        caloriesHistory = new ArrayList<>();
        caloriesToday = new CalorieDay(LocalDate.now());
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

    public List<CalorieDay> getCaloriesHistory() {
        return caloriesHistory;
    }

    public void addCaloriesHistory(CalorieDay calorieDay) {
        caloriesHistory.add(calorieDay);
    }

    public CalorieDay getCaloriesToday() {
        return caloriesToday;
    }

    public void setCaloriesToday(CalorieDay caloriesToday) {
        this.caloriesToday = caloriesToday;
    }

    public void setPrescribed(boolean prescribed) {
        isPrescribed = prescribed;
    }

    public boolean isPrescribed() {
        return isPrescribed;
    }


}
