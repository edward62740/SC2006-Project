package com.nutriroute.controllers;

import android.util.Pair;

import com.nutriroute.enums.MealType;
import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IGenericUserManagementService;
import com.nutriroute.interfaces.IUserCalorieManagementService;
import com.nutriroute.interfaces.IUserFoodService;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.Restaurant;
import com.nutriroute.services.GenericUserManagementService;
import com.nutriroute.services.UserCalorieManagementService;
import com.nutriroute.services.UserFoodService;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the user controller which is responsible for handling the the logic for the user.
 * The distinction between the controller and the activities/fragments is that the controller
 * is the one that deals with the service layer.
 */
public class UserController {

    // Service layer objects
    private static final IUserCalorieManagementService userCalorieManagementService = new UserCalorieManagementService();
    private static final IUserFoodService userFoodService = new UserFoodService();
    private static final IGenericUserManagementService userManagementService = new GenericUserManagementService();
    // Data store reference
    private static final IDataStore<String> dataStore = ServiceLocator.getDB();

    private UserController() {
    }

    /**
     * High level abstraction for updating the user state, which keeps the underlying data
     * structures valid and up-to-date.
     */
    public static void updateUserState() {
        userCalorieManagementService.updateState(); // updates the state of the user's calorie history and current day
    }

    /**
     * These methods are part of use cases #1-1, which are used to instantiate a new user
     */
    public static void registerUser(String name, String password, String email) {
        userManagementService.registerGenericUser(name, password, email, UserType.USER);
        //dataStore._loadObjFromDB(GenericUser, null);
    }


    /**
     * These methods are part of use cases #1-2, 1-4, #1-5, which are used to give the user
     * a list of relevant restaurants
     */
    public static Pair<List<Restaurant>, List<Float>> queryRestaurantsByDistance() {
        return userFoodService.queryRestaurants(userFoodService.sortByDistance());
    }

    public static Pair<List<Restaurant>, List<Float>> queryRestaurantsByPrescription() {
        Pair<List<Restaurant>, List<Float>> restaurants =
                userFoodService.queryRestaurants(userFoodService.sortByDistance());

        if (restaurants != null && restaurants.first != null && !restaurants.first.isEmpty()) {

            int i = (int) (Math.random() * restaurants.first.size());

            List<Restaurant> selectedRestaurant = new ArrayList<>();
            selectedRestaurant.add(restaurants.first.get(i));

            List<Float> selectedDistance = new ArrayList<>();
            selectedDistance.add(restaurants.second.get(i));

            return new Pair<>(selectedRestaurant, selectedDistance);
        }

        return new Pair<>(new ArrayList<>(), new ArrayList<>());
    }


    public static void setUserCurrentLocation(String userLatLong) {
        userFoodService.setUserLatLong(userLatLong);
    }

    public static String getUserCurrentLocation() {
        return userFoodService.getUserLatLong();
    }

    /**
     * These methods are part of use cases #1-6, which are used to update the calories of the
     * current user
     */
    public static void updateCalories(String restaurantId, String foodId, int calories, MealType mealType) {
        userCalorieManagementService.addCalorieItem(restaurantId, foodId, calories, mealType);
    }

    /**
     * Log out method
     */
    public static void logoutAndExit() {
        updateUserState();
        AuthController.logout();
    }


}
