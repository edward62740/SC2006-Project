package com.nutriroute.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.AdminRestaurantRequestAdapter;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.models.Admin;
import com.nutriroute.models.Request;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class AdminRestaurantClaimsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdminRestaurantRequestAdapter restaurantAdapter;

    Admin currentUser = ((Admin) AuthStore.getCurUser());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_fragment_claims, container, false);


        recyclerView = view.findViewById(R.id.adminClaimRestaurantRecycler);


        // Setup RecyclerView for claimed restaurants
        recyclerView = view.findViewById(R.id.adminClaimRestaurantRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Request<String>> requests = AdminController.getRestaurantClaimRequests();
        System.out.println(requests);
        // remove if request type is not claim request using lambda
       // requests.removeIf(request -> !request.getType().equals(RequestType.CLAIM_REQUEST));
        if (requests==null)
            recyclerView.setVisibility(View.GONE);
        else {
            restaurantAdapter = new AdminRestaurantRequestAdapter(getContext(), requests);
            recyclerView.setAdapter(restaurantAdapter);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
