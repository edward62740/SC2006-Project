package com.nutriroute.interfaces;

import com.nutriroute.enums.MealType;

public interface IUserCalorieManagementService {

    void updateState();
    void addCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);
    void removeCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);

}
