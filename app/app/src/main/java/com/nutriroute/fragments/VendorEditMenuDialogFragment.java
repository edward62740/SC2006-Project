package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.VendorMenuAdapter;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.Vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorEditMenuDialogFragment extends DialogFragment {

    private Restaurant restaurant;
    private Menu menu;
    private RecyclerView menuRecyclerView;
    private MenuRequest menuRequest;

    public VendorEditMenuDialogFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.menu = restaurant.getMenu();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_menu, container, false);

        ImageView restaurantImage = view.findViewById(R.id.restaurantImage);
        TextView restaurantName = view.findViewById(R.id.restaurantName);
        Button saveChanges = view.findViewById(R.id.saveChangesButton);
        ImageButton backButton = view.findViewById(R.id.backButton);

        menuRecyclerView = view.findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        menuRecyclerView.setNestedScrollingEnabled(false);

        //TODO: will probably change logic here to parse a new request
        List<MenuItem> editItemList = new ArrayList<>();
        for (int i=0; i<menu.size(); i++) {
            editItemList.add(new MenuItem());
            editItemList.get(i).setPrice(-1);
            editItemList.get(i).setCalories(-1);
        }
        VendorMenuAdapter menuAdapter = new VendorMenuAdapter(getContext(), menu, editItemList);
        menuRecyclerView.setAdapter(menuAdapter);

        restaurantName.setText(restaurant.getName());

        saveChanges.setOnClickListener(v -> {
            boolean change=false;
            boolean valid=true;
            for (int i=0; i<menu.size(); i++){
                if (!editItemList.get(i).noChange() && !editItemList.get(i).changeIsValid())
                    valid=false;
                if (editItemList.get(i).changeIsValid() && !menu.get(i).equals(editItemList.get(i))){
                    VendorController.generateNewMenuRequest(menu, i, editItemList.get(i));
                    change=true;
                }
            }
            if (change) {
                Toast.makeText(getContext(), "Request Submitted", Toast.LENGTH_SHORT).show();
                dismiss();
            }else if (!valid)
                Toast.makeText(getContext(), "Invalid Entry Detected", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "No Change Detected", Toast.LENGTH_SHORT).show();
            //TODO: implement logic to submit editMenuRequest
        });

        backButton.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

}
