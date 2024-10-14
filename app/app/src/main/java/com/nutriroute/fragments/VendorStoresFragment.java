package com.nutriroute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.VendorRestaurantAdapter;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class VendorStoresFragment extends Fragment {
    private RecyclerView recyclerView;
    private VendorRestaurantAdapter restaurantAdapter;
    private TextView textTitle;
    private Button claimNewStoreButton;

    Vendor currentUser = ((Vendor) AuthStore.getCurUser());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vendor_fragment_stores, container, false);

        //Initialize views
        textTitle = view.findViewById(R.id.vendor_restaurants_title);
        recyclerView = view.findViewById(R.id.recyclerViewClaimedStores);
        claimNewStoreButton = view.findViewById(R.id.claimNewStoreButton);


        // Setup RecyclerView for claimed restaurants
        recyclerView = view.findViewById(R.id.recyclerViewClaimedStores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Restaurant> restaurants = VendorController.getRestaurantList();
        if (restaurants==null)
            recyclerView.setVisibility(View.GONE);
        else {
            restaurantAdapter = new VendorRestaurantAdapter(getContext(), restaurants);
            recyclerView.setAdapter(restaurantAdapter);
            recyclerView.setVisibility(View.VISIBLE);
        }

        claimNewStoreButton.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new VendorClaimStoreFragment()).commit();
        });

        return view;
    }
}
