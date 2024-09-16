package com.nutriroute.models;

public class User extends GenericUser<Double> {

    private int height;
    private int weight;
    private int targetCalories;
    private int targetWeight;

    public User(String name, String email, String password, Double id) {
        super(name, email, password, id);
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
}
