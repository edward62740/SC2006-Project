package com.nutriroute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.VendorClaimsAdapter;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Request;

import java.util.Collections;
import java.util.List;

public class VendorStatusFragment extends Fragment {
    private TextView textTitle, textNoClaims;
    private RecyclerView recyclerView;
    private VendorClaimsAdapter claimsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vendor_fragment_status, container, false);

        textTitle = view.findViewById(R.id.vendor_claims_title);
        textNoClaims = view.findViewById(R.id.text_noclaim);
        recyclerView = view.findViewById(R.id.recycler_view_claims);

        if (VendorController.getNumberOfRequests()==0) { // TODO: change logic to if no claims
            textNoClaims.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else { //TODO: Implement logic to populate recyclerView
            List<Request<String>> requestList = VendorController.getRequests();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            claimsAdapter = new VendorClaimsAdapter(getContext(), requestList);
            recyclerView.setAdapter(claimsAdapter);
            textNoClaims.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
