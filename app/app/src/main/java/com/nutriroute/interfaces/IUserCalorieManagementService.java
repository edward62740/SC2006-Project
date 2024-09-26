package com.nutriroute.interfaces;

public interface IUserCalorieManagementService {

    void updateState();
    void addCalorieItem(String restaurantId, String foodId, int calories);
    void removeCalorieItem(String restaurantId, String foodId, int calories);

}
