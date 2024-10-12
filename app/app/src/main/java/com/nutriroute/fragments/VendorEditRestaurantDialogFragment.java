package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.VendorMenuAdapter;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

public class VendorEditRestaurantDialogFragment extends DialogFragment {

    private Restaurant restaurant;
    private RestaurantRequest restaurantRequest;

    public VendorEditRestaurantDialogFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_restaurant_info, container, false);

        ImageView restaurantImage = view.findViewById(R.id.image_restaurant);
        EditText restaurantName = view.findViewById(R.id.text_name);
        EditText openHour = view.findViewById(R.id.text_open_hour);
        EditText closeHour = view.findViewById(R.id.text_close_hour);
        EditText restaurantDescription = view.findViewById(R.id.text_description);
        Button saveChanges = view.findViewById(R.id.button_save_changes);
        ImageButton backButton = view.findViewById(R.id.button_back);

        restaurantName.setText(restaurant.getName());
        restaurantDescription.setText(restaurant.getDescription());
        openHour.setText(restaurant.getOpenHour()==null ? "--:--":restaurant.getOpenHour().toString());
        closeHour.setText(restaurant.getCloseHour()==null ? "--:--":restaurant.getCloseHour().toString());

        // Make sure time is valid and formatted
        openHour.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                // preserve the format of currency
                if(!s.toString().equals(current)) {
                    openHour.removeTextChangedListener(this);

                    String formatted = processTime(s);

                    current = formatted;
                    openHour.setText(formatted);
                    openHour.setSelection(formatted.length());

                    openHour.addTextChangedListener(this);
                    //TODO set request open hour
                }
            }
        });
        closeHour.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                // preserve the format of currency
                if(!s.toString().equals(current)) {
                    closeHour.removeTextChangedListener(this);

                    String formatted = processTime(s);

                    current = formatted;
                    closeHour.setText(formatted);
                    closeHour.setSelection(formatted.length());

                    closeHour.addTextChangedListener(this);
                    //TODO set request close hour
                }
            }
        });

        restaurantImage.setOnClickListener(v -> {
            //TODO: Implement logic to upload image
        });

        saveChanges.setOnClickListener(v -> {
            //TODO: implement logic to submit editRestaurantRequest
            dismiss();
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

    private String processTime(CharSequence s){
        Integer number = Integer.parseInt(s.toString().replaceAll("[:,-]", ""));
        if (number.toString().length()>4)
            number /= 10;
        int hour = number/100;
        int minute = number%100;
        if (hour > 23)
            hour=23;
        if (minute > 59)
            minute=59;
        return (hour<10 ? "0" + hour : hour) + ":" + (minute<10 ? "0" + minute : minute);
    }

}
