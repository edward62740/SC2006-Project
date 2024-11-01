package com.nutriroute.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.models.Restaurant;
import com.nutriroute.utils.PostalCodeHelper;

public class RestaurantDialogFragment extends DialogFragment {

    private Restaurant restaurant;
    private PostalCodeHelper postalCodeHelper = new PostalCodeHelper();
    private RecyclerView menuRecyclerView;

    public RestaurantDialogFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_restaurant_info, container, false);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        TextView dialogAddress = view.findViewById(R.id.dialog_address);
        TextView dialogPhone = view.findViewById(R.id.dialog_phone);
        TextView dialogDescription = view.findViewById(R.id.dialog_description);
        WebView mapWebView = view.findViewById(R.id.map_webview);
        mapWebView.getSettings().setJavaScriptEnabled(true);
        AppCompatImageButton dialogExit = view.findViewById(R.id.exit_button);

        menuRecyclerView = view.findViewById(R.id.restaurant_menu);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MenuAdapter menuAdapter = new MenuAdapter(restaurant, getContext());
        menuRecyclerView.setAdapter(menuAdapter);

        postalCodeHelper.fromPostalCodeGetAddress(Integer.parseInt(restaurant.getAddress()), address -> {
            if (address != null) {
                dialogAddress.setText("Address: " + address);
            } else {
                dialogAddress.setText("Invalid address");
            }
        });

        dialogTitle.setText(restaurant.getName());
        dialogPhone.setText("Phone: " + restaurant.getPhone());
        dialogDescription.setText("Overview: " + restaurant.getDescription());

        final String[] url = {"https://www.onemap.gov.sg/amm/amm.html?mapStyle=Default&zoomLevel=13"};
        url[0] += "&marker=postalcode:" + restaurant.getAddress() + "!colour:red!rType:TRANSIT!rDest:" + restaurant.getLocation();

        mapWebView.loadUrl(url[0]);

        dialogExit.setOnClickListener(v -> dismiss());

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
