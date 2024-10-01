package com.nutriroute.services;

import android.util.Pair;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IUserFoodService;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.GNSSLocHelper;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class UserFoodService implements IUserFoodService {
    User user = (User) AuthStore.getCurUser(); // unsafe cast to User since it must be User by now
    static IDataStore<String> dataStore = ServiceLocator.getDB();
    GNSSLocHelper gnssLocHelper = new GNSSLocHelper();

    private double userLatitude;
    private double userLongitude;

    public UserFoodService() {
    }
    public void setUserLatLong(String userLatLong) {
        String[] latLongArray = userLatLong.split(",");
        userLatitude = Double.parseDouble(latLongArray[0]);
        userLongitude = Double.parseDouble(latLongArray[1]);
    }
    public String getUserLatLong() {
        return userLatitude + "," + userLongitude;
    }

    @Override
    public Pair<List<Restaurant>, List<Float>> queryRestaurants(Comparator<Restaurant> sortingFunction) {
        Collection<Restaurant> restaurantCollection = dataStore.getRestaurants();

        List<Restaurant> restaurantList = new ArrayList<>(restaurantCollection);
        // If restaurant.getLatlong is null, get it
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getLocation() == null) {
                gnssLocHelper.fromAddressGetCoordinates(restaurant.getAddress(), (latlong, conf) -> {
                    if (latlong != null) {
                        restaurant.setLocation(latlong);
                    }
                    else {
                        restaurant.setLocation("null");
                    }
                    dataStore.setRestaurant(restaurant);
                });

            }
        }

        if (sortingFunction == null) {
            sortingFunction = sortByDistance();
        }

        restaurantList.sort(sortingFunction);

        // Calculate the distance of each restaurant from the user
        List<Float> distances = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            System.out.println("Restaurant: " + (float) calculateDistance(userLatitude, userLongitude, restaurant));
            System.out.println(userLatitude + " " + userLongitude + " " + restaurant.getLocation());
            distances.add((float) calculateDistance(userLatitude, userLongitude, restaurant));
        }

        return new Pair<>(restaurantList, distances);
    }

    @Override
    public Comparator<Restaurant> sortByDistance() {
        return new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                double distance1 = calculateDistance(userLatitude, userLongitude, r1);
                double distance2 = calculateDistance(userLatitude, userLongitude, r2);
                return Double.compare(distance1, distance2);
            }
        };
    }

    private double calculateDistance(double userLat, double userLong, Restaurant restaurant) {
        if (restaurant.getLocation() == null || restaurant.getLocation().equals("null")) {
            return Double.MAX_VALUE; // inf distance
        }

        String[] latLongArray = restaurant.getLocation().split(",");
        double restaurantLat = Double.parseDouble(latLongArray[0]);
        double restaurantLong = Double.parseDouble(latLongArray[1]);

        // haversine formula to calculate the distance between two points
        final int R = 6371;
        double latDistance = Math.toRadians(restaurantLat - userLat);
        double lonDistance = Math.toRadians(restaurantLong - userLong);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(restaurantLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R*c;
    }
}
