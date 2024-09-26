package com.nutriroute.services;

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

        user.getCaloriesHistory().removeIf(day -> day.getCaloriesConsumed().isEmpty());
    }

    public void addCalorieItem(String restaurantId, String foodId, int calories) {
        updateState();
        user.getCaloriesToday().addCaloriesConsumed(calories);
        user.getCaloriesToday().addFoodConsumed(foodId);
        user.getCaloriesToday().addFoodRestaurant(restaurantId);

    }

    public void removeCalorieItem(String restaurantId, String foodId, int calories) {
        updateState();
        user.getCaloriesToday().getCaloriesConsumed().remove(calories);
        user.getCaloriesToday().getFoodConsumed().remove(foodId);
        user.getCaloriesToday().getFoodRestaurant().remove(restaurantId);
    }
}
