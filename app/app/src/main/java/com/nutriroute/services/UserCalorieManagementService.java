package com.nutriroute.services;

import static com.nutriroute.utils.Consts.MAX_DAYS_HISTORY;

import com.nutriroute.enums.MealType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IUserCalorieManagementService;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.time.LocalDate;
import java.util.Comparator;

public class UserCalorieManagementService implements IUserCalorieManagementService {
    User user = (User) AuthStore.getCurUser(); // unsafe cast to User since it must be User by now
    static IDataStore<String> dataStore = ServiceLocator.getDB();

    public void updateState() {
        if (user == null) {
            return;
        }

        _todayStateTransition(); // update state for handling today's calories
        user.getCaloriesHistory().removeIf(calorieDay -> calorieDay.getDate().isBefore(LocalDate.now().minusDays(MAX_DAYS_HISTORY))); // remove history older than 7 days
        // check ordering is ascending by day
        user.getCaloriesHistory().sort(Comparator.comparing(CalorieDay::getDate));
        dataStore.setUser(user, user.getId()); // update since mutated
    }

    private void _todayStateTransition() {
        // ensure existence of caloriesToday

        if (user.getCaloriesToday() == null) {

            System.out.println("CaloriesToday is null");
            user.setCaloriesToday(new CalorieDay(LocalDate.now()));
        }
        else if (!user.getCaloriesToday().getDate().equals(LocalDate.now())) { // state update if not today
            user.addCaloriesHistory(user.getCaloriesToday());
            user.setCaloriesToday(new CalorieDay(LocalDate.now()));
        }

        // ensure existence of sub-attributes because gson deserialization returns null for empty lists
        if (user.getCaloriesToday().getCaloriesConsumed() == null) {
            user.getCaloriesToday().initCaloriesConsumed();
        }
        if (user.getCaloriesToday().getFoodConsumed() == null) {
            user.getCaloriesToday().initFoodConsumed();
        }
        if (user.getCaloriesToday().getFoodRestaurant() == null) {
            user.getCaloriesToday().initFoodRestaurant();
        }
        dataStore.setUser(user, user.getId()); // update since mutated
    }

    public void addCalorieItem(String restaurantId, String foodId, int calories, MealType mealType) {
        updateState();
        if (mealType != MealType.MISC) {
            user.getCaloriesToday().addCaloriesConsumed(calories, mealType.ordinal());
            user.getCaloriesToday().addFoodConsumed(foodId, mealType.ordinal());
            user.getCaloriesToday().addFoodRestaurant(restaurantId, mealType.ordinal());
        }
        else {
            // append to the end of the list with size()
            user.getCaloriesToday().addCaloriesConsumed(calories, user.getCaloriesToday().getCaloriesConsumed().size());
            user.getCaloriesToday().addFoodConsumed(foodId, user.getCaloriesToday().getFoodConsumed().size());
            user.getCaloriesToday().addFoodRestaurant(restaurantId, user.getCaloriesToday().getFoodRestaurant().size());

        }
        dataStore.setUser(user, user.getId()); // update since mutated
    }

    public void removeCalorieItem(String restaurantId, String foodId, int calories, MealType mealType) {
        updateState();
        user.getCaloriesToday().getCaloriesConsumed().remove(calories);
        user.getCaloriesToday().getFoodConsumed().remove(foodId);
        user.getCaloriesToday().getFoodRestaurant().remove(restaurantId);
        dataStore.setUser(user, user.getId()); // update since mutated
    }

}
