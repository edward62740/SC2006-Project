package com.nutriroute.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.UserRestaurantAdapter;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IUserFoodService;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.services.UserFoodService;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.GNSSLocHelper;
import com.nutriroute.utils.PostalCodeHelper;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserStoresFragment extends Fragment {

    private WebView webView;
    IDataStore<String> dataStore = ServiceLocator.getDB();
    IUserFoodService userFoodService = new UserFoodService();
    User currentUser = (User) AuthStore.getCurUser();
    private RecyclerView recyclerView;
    private UserRestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;
    private View skeletonView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.user_fragment_stores, container, false);

        // Initialize the WebView
//        webView = view.findViewById(R.id.webview);
       // webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
      //  webView.getSettings().setJavaScriptEnabled(true);
        PostalCodeHelper postalCodeHelper = new PostalCodeHelper();
        GNSSLocHelper gnssLocHelper = new GNSSLocHelper();
        recyclerView = view.findViewById(R.id.recycler_view_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.clearOnScrollListeners();

        restaurantAdapter = new UserRestaurantAdapter(this.getContext(), userFoodService.queryRestaurants(null));
        recyclerView.setAdapter(restaurantAdapter);
        skeletonView = view.findViewById(R.id.skeleton_view);
        skeletonView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        loadData();
        Toast.makeText(getContext(), " decode postcode to human readable addr", Toast.LENGTH_LONG).show();



/*
        // Load the Google Maps URL for a specific location (San Francisco example)
        final String[] url = {"https://www.onemap.gov.sg/amm/amm.html?mapStyle=Default&zoomLevel=15"};
        dataStore.getRestaurants().forEach(restaurant -> {
            url[0] += "&marker=postalcode:" + restaurant.getAddress() + "!colour:red";
            postalCodeHelper.fromPostalCodeGetAddress(Integer.parseInt(restaurant.getAddress()), address -> {
                if (address != null) {
                    // Handle the received address (e.g., display it in a TextView)
                    System.out.println("Address: " + address);
                    // Using a lambda expression for the AddressCallback

                    gnssLocHelper.fromAddressGetCoordinates(address, (latitude, longitude) -> {
                        if (latitude != null && longitude != null) {
                            System.out.println("Coordinates: Latitude = " + latitude + ", Longitude = " + longitude);
                            // Use the coordinates here (e.g., update UI, save to database, etc.)
                        } else {
                            System.out.println("Failed to retrieve coordinates.");
                        }
                    });


                } else {
                    // Handle the error (e.g., show a message to the user)
                    System.out.println("Address not found.");
                }
            });


        });
        webView.loadUrl(url[0]);
*/
        return view;
    }


    private void loadData() {
        // Create a single-threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Run the data loading in a background thread
        executor.execute(() -> {
            try {
                Thread.sleep(550);


                getActivity().runOnUiThread(() -> {

                    // Hide skeleton and show RecyclerView
                    skeletonView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Shutdown the executor after the task is completed (optional)
        executor.shutdown();
    }


}