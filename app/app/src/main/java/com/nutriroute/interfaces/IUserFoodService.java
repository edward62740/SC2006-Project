package com.nutriroute.interfaces;

import android.util.Pair;

import com.nutriroute.models.Restaurant;

import java.util.Comparator;
import java.util.List;

public interface IUserFoodService {
    /*
     * Query the restaurants based on the sorting function
     */
    Pair<List<Restaurant>, List<Float>> queryRestaurants(Comparator<Restaurant> sortingFunction);

    Comparator<Restaurant> sortByDistance();

    void setUserLatLong(String userLatLong);
    String getUserLatLong();
}
