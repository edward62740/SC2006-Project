package com.nutriroute.interfaces;

import com.nutriroute.models.Restaurant;

import java.util.Comparator;
import java.util.List;

public interface IUserFoodService {
    /*
     * Query the restaurants based on the sorting function
     */
    List<Restaurant> queryRestaurants(Comparator<Restaurant> sortingFunction);

    Comparator<Restaurant> sortByDistance();
}
