package com.nutriroute.services;

import com.nutriroute.enums.MealType;
import com.nutriroute.interfaces.IUserCalorieManagementService;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;

import java.time.LocalDate;

public class UserCalorieManagementService implements IUserCalorieManagementService {
    User user = (User) AuthStore.getCurUser(); // unsafe cast to User since it must be User

    public void updateState() {
        if (user.getCaloriesToday() == null) {

            System.out.println("CaloriesToday is null");
            user.setCaloriesToday(new CalorieDay(LocalDate.now()));
        }
        else if (!user.getCaloriesToday().getDate().equals(LocalDate.now())) {
            user.addCaloriesHistory(user.getCaloriesToday());
            user.setCaloriesToday(new CalorieDay(LocalDate.now()));
        }
        if (user.getCaloriesToday().getCaloriesConsumed() == null) {
            user.getCaloriesToday().initCaloriesConsumed();
        }
        if (user.getCaloriesToday().getFoodConsumed() == null) {
            user.getCaloriesToday().initFoodConsumed();
        }
        if (user.getCaloriesToday().getFoodRestaurant() == null) {
            user.getCaloriesToday().initFoodRestaurant();
        }

//        user.getCaloriesHistory().removeIf(day -> day == null || day.getCaloriesConsumed().isEmpty());
    }

    public void addCalorieItem(String restaurantId, String foodId, int calories, MealType mealType) {
        updateState();
        user.getCaloriesToday().addCaloriesConsumed(calories, mealType.ordinal());
        user.getCaloriesToday().addFoodConsumed(foodId, mealType.ordinal());
        user.getCaloriesToday().addFoodRestaurant(restaurantId, mealType.ordinal());
        if (mealType == MealType.MISC) {
            // append to the end of the list with size()
            user.getCaloriesToday().addCaloriesConsumed(calories, user.getCaloriesToday().getCaloriesConsumed().size());
            user.getCaloriesToday().addFoodConsumed(foodId, user.getCaloriesToday().getFoodConsumed().size());
            user.getCaloriesToday().addFoodRestaurant(restaurantId, user.getCaloriesToday().getFoodRestaurant().size());

        }

    }

    public void removeCalorieItem(String restaurantId, String foodId, int calories, MealType mealType) {
        updateState();
        user.getCaloriesToday().getCaloriesConsumed().remove(calories);
        user.getCaloriesToday().getFoodConsumed().remove(foodId);
        user.getCaloriesToday().getFoodRestaurant().remove(restaurantId);
    }
}
