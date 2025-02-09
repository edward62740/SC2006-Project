package com.nutriroute.fragments;


import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.UserRestaurantAdapter;
import com.nutriroute.controllers.UserController;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IUserFoodService;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.services.UserFoodService;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.GNSSLocHelper;
import com.nutriroute.utils.LocationHelper;
import com.nutriroute.utils.PostalCodeHelper;
import com.nutriroute.utils.ServiceLocator;
import android.Manifest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserStoresFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserRestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;
    private View skeletonView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private LocationHelper locationHelper;
    public static String userLatLong;
    private static User currentUser = (User) AuthStore.getCurUser();
    private TextView searchTitle;

    public static String getUserLatLong() {
        return userLatLong;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.user_fragment_stores, container, false);
        locationHelper = new LocationHelper(this.getContext());
        searchTitle = view.findViewById(R.id.stores_search_title);

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Get the last known location
            getLocation();
        }
        if(currentUser.isPrescribed())
        {
            searchTitle.setText("Recommended Restaurants");
        }
        else
        {
            searchTitle.setText("Nearby Restaurants");
        }

        recyclerView = view.findViewById(R.id.recycler_view_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        skeletonView = view.findViewById(R.id.skeleton_view);
        skeletonView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        return view;
    }

    private void getLocation() {
        locationHelper.getLastLocation(new LocationHelper.LocationCallback() {

            @Override
            public void onLocationReceived(Location location) {
                UserController.setUserCurrentLocation(location.getLatitude() + "," + location.getLongitude());


               //Toast.makeText(getContext(), "Setting static location for testing", Toast.LENGTH_SHORT).show();

                UserController.setUserCurrentLocation("1.35310,103.68909");
                userLatLong = UserController.getUserCurrentLocation();

                queryAndSetupAdapter();
            }

            @Override
            public void onLocationError(String errorMessage) {
                Toast.makeText(getContext(), "Failed to get location: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void queryAndSetupAdapter() {
        // Create a single-threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {

            // Query restaurants and sort by distance
            Pair<List<Restaurant>, List<Float>> restaurants;

            if (currentUser.isPrescribed())
            {
                restaurants = UserController.queryRestaurantsByPrescription();
            }
            else {

                restaurants = UserController.queryRestaurantsByDistance();
            }
            getActivity().runOnUiThread(() -> {
                // Setup the adapter with the queried restaurants
                restaurantAdapter = new UserRestaurantAdapter(getContext(), restaurants);
                recyclerView.setAdapter(restaurantAdapter);

                // Hide skeleton and show RecyclerView
                skeletonView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
        executor.shutdown();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
