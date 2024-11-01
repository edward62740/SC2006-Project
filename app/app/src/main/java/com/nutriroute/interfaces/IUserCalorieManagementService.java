package com.nutriroute.interfaces;

import com.nutriroute.enums.MealType;

/**
 * This is the interface user calorie management.
 * In general, it provides methods to update the state of the user's calorie count,
 * as well as add and remove calorie items.
 */
public interface IUserCalorieManagementService {

    /*
    * Updates the internal attributes of the model class User to reflect the current calorie state.
    * Depends on the database sync state, and the local device time.
    */
    void updateState();
    void addCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);
    void removeCalorieItem(String restaurantId, String foodId, int calories, MealType mealType);

}
