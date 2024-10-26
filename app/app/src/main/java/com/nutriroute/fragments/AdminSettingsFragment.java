package com.nutriroute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.VendorController;

public class AdminSettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_fragment_settings, container, false);

        Button logoutButton = view.findViewById(R.id.admin_button_logout);

        logoutButton.setOnClickListener(v -> {
            AdminController.logoutAndExit();
            requireActivity().finish();
            getFragmentManager().beginTransaction().remove(this).commit();
        });

        return view;
    }

}
