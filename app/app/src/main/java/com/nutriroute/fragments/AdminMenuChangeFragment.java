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
import com.nutriroute.adapters.AdminMenuUpdateAdapter;
import com.nutriroute.adapters.AdminRestaurantRequestAdapter;
import com.nutriroute.adapters.VendorRestaurantAdapter;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.enums.RequestType;
import com.nutriroute.models.Admin;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class AdminMenuChangeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdminMenuUpdateAdapter menuUpdateAdapter;

    Admin currentUser = ((Admin) AuthStore.getCurUser());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_fragment_menu, container, false);


        recyclerView = view.findViewById(R.id.adminChangeMenuRecycler);


        // Setup RecyclerView for claimed restaurants
        recyclerView = view.findViewById(R.id.adminChangeMenuRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Request<String>> requests = AdminController.getMenuChangeRequests();
        System.out.println(requests);
        // remove if request type is not claim request using lambda
        // requests.removeIf(request -> !request.getType().equals(RequestType.CLAIM_REQUEST));
        if (requests==null)
            recyclerView.setVisibility(View.GONE);
        else {
            menuUpdateAdapter = new AdminMenuUpdateAdapter(getContext(), requests);
            recyclerView.setAdapter(menuUpdateAdapter);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
