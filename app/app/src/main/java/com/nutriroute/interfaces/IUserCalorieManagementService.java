package com.nutriroute.interfaces;

import com.nutriroute.enums.MealType;

public interface IUserCalorieManagementService {

    /*
    * Updates the internal attributes of the model class User to reflect the current calorie state.
    * Depends on the database sync state, and the local device time.
    */
    void updateState();
    void addCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);
    void removeCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);

}
