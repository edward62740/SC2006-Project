package com.nutriroute.fragments;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.nutriroute.R;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;

import java.time.LocalTime;

public class VendorClaimStoreFragment extends Fragment {
    private ImageButton backButton;
    private TextView textTitle;
    private EditText restaurantName, openHour, closeHour, restaurantAddress, restaurantPhone,
            restaurantEmail, restaurantWebsite, restaurantDescription;
    private ImageView proofImage;
    private Button browseButton, submitButton;

    Vendor currentUser = ((Vendor) AuthStore.getCurUser());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vendor_claim_store, container, false);

        //Initialise views
        backButton = view.findViewById(R.id.back_button);
        textTitle = view.findViewById(R.id.text_title);
        restaurantName = view.findViewById(R.id.restaurant_name);
        openHour = view.findViewById(R.id.open_hour);
        closeHour = view.findViewById(R.id.close_hour);
        browseButton = view.findViewById(R.id.browse_button);
        submitButton = view.findViewById(R.id.submit_button);
        restaurantAddress = view.findViewById(R.id.restaurant_address);
        restaurantPhone = view.findViewById(R.id.restaurant_phone);
        restaurantEmail = view.findViewById(R.id.restaurant_email);
        restaurantWebsite = view.findViewById(R.id.restaurant_website);
        restaurantDescription = view.findViewById(R.id.restaurant_description);

        if (currentUser.getRestaurants()!=null)
            textTitle.setText("Claim a new store!");
        else
            textTitle.setText("Let's start by claiming your first store!");

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
                    //TODO set request open hout
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

        browseButton.setOnClickListener(v -> {
            //TODO: Implement logic to upload image
        });

        submitButton.setOnClickListener(v -> {
            if (checkFields()){
                String name = restaurantName.getText().toString();
                LocalTime open = LocalTime.parse(openHour.getText().toString());
                LocalTime close = LocalTime.parse(closeHour.getText().toString());
                String address = restaurantAddress.getText().toString();
                String phone = restaurantPhone.getText().toString();
                String email = restaurantEmail.getText().toString();
                String website = restaurantWebsite.getText().toString();
                String description = restaurantDescription.getText().toString();
                Restaurant restaurant = new Restaurant(name, open, close, address, phone, email, website, description);
                VendorController.generateNewRestaurantClaimRequest(restaurant, "proof1"); //todo add proof
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new VendorStoresFragment()).commit();
                Toast.makeText(getContext(), "Claim Request Submitted", Toast.LENGTH_SHORT).show();
            }
            else {
                if (!checkTime(openHour.getText().toString()) || !checkTime(closeHour.getText().toString()))
                    Toast.makeText(getContext(), "Invalid time!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Missing fields!", Toast.LENGTH_SHORT).show();
            }


            //Restaurant restaurant = new Restaurant();
            //VendorController.generateNewMenuRequest(restaurant); // just to test
            //TODO: Implement logic to submit claim request
        });

        backButton.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new VendorStoresFragment()).commit();
        });


        return view;
    }

    private String processTime(CharSequence s){
        String cleanString = s.toString().replaceAll("[:,-]", "");
        if (cleanString.isEmpty()) return "00:00";
        Integer number = Integer.parseInt(cleanString);
        if (number.toString().length()>4)
            number /= 10;
        int hour = number/100;
        int minute = number%100;
        if (hour > 23)
            hour=23;
        if (hour>1 && minute > 59)
            minute=59;
        return (hour<10 ? "0" + hour : hour) + ":" + (minute<10 ? "0" + minute : minute);
    }

    private Boolean checkTime(String s){
        String cleanString = s.replaceAll("[:,-]", "");
        if (cleanString.isEmpty()) return false;
        Integer number = Integer.parseInt(cleanString);
        if (number.toString().length()>4)
            number /= 10;
        int hour = number/100;
        int minute = number%100;
        if (hour > 23)
            hour=23;
        if (hour>1 && minute > 59)
            minute=59;
        return minute <= 59;
    }

    private Boolean checkFields() {
        return !restaurantName.getText().toString().isEmpty() &&
                !openHour.getText().toString().isEmpty() &&
                checkTime(openHour.getText().toString()) &&
                !closeHour.getText().toString().isEmpty() &&
                !openHour.getText().toString().equals(closeHour.getText().toString()) &&
                checkTime(closeHour.getText().toString()) &&
                !restaurantAddress.getText().toString().isEmpty() &&
                !restaurantPhone.getText().toString().isEmpty();
        //todo add validation for proof image
    }
}
