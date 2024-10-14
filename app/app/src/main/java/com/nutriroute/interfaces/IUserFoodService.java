package com.nutriroute.interfaces;

import android.util.Pair;

import com.nutriroute.models.Restaurant;

import java.util.Comparator;
import java.util.List;

/**
 * This is the interface which provides the implementation of the user food service.
 * Specifically, this performs use case #1-4.
 * The pre-condition is that the GNSS service is enabled and the user has a valid location.
 */
public interface IUserFoodService {
    /*
     * Query the restaurants based on the sortingFunction. Returns a pair of Restaurant list and
     * a list of floats representing the distance of each restaurant from the user.
     */
    Pair<List<Restaurant>, List<Float>> queryRestaurants(Comparator<Restaurant> sortingFunction);

    Comparator<Restaurant> sortByDistance();

    void setUserLatLong(String userLatLong);
    String getUserLatLong();
}
