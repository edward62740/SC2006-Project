package com.nutriroute.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nutriroute.R;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.utils.GNSSLocHelper;
import com.nutriroute.utils.PostalCodeHelper;
import com.nutriroute.utils.ServiceLocator;

public class UserStoresFragment extends Fragment {

    private WebView webView;
    IDataStore<String> dataStore = ServiceLocator.getDB();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.user_fragment_stores, container, false);

        // Initialize the WebView
        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
        webView.getSettings().setJavaScriptEnabled(true);
        PostalCodeHelper postalCodeHelper = new PostalCodeHelper();
        GNSSLocHelper gnssLocHelper = new GNSSLocHelper();


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

        return view;
    }
}