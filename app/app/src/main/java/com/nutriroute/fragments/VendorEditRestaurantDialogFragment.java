package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nutriroute.R;
import com.nutriroute.adapters.VendorMenuAdapter;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

import java.time.LocalTime;

public class VendorEditRestaurantDialogFragment extends DialogFragment {

    private Restaurant restaurant;
    private RestaurantRequest restaurantRequest;
    ImageView restaurantImage;
    EditText restaurantName, openHour, closeHour, restaurantDescription,
    restaurantAddress, restaurantPhone, restaurantEmail, restaurantWebsite;
    Button saveChanges;
    ImageButton backButton;
    String imageURL;

    public VendorEditRestaurantDialogFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_restaurant_info, container, false);

        restaurantImage = view.findViewById(R.id.image_restaurant);
        restaurantName = view.findViewById(R.id.text_name);
        openHour = view.findViewById(R.id.text_open_hour);
        closeHour = view.findViewById(R.id.text_close_hour);
        restaurantDescription = view.findViewById(R.id.restaurant_description);
        saveChanges = view.findViewById(R.id.button_save_changes);
        backButton = view.findViewById(R.id.button_back);
        restaurantAddress = view.findViewById(R.id.restaurant_address);
        restaurantPhone = view.findViewById(R.id.restaurant_phone);
        restaurantEmail = view.findViewById(R.id.restaurant_email);
        restaurantWebsite = view.findViewById(R.id.restaurant_website);

        Glide.with(getContext()).load(restaurant.getImage()).into(restaurantImage);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (restaurantImage.getDrawable()==null)
                    Glide.with(getContext()).load(R.drawable.no_image_available).into(restaurantImage);
            }
        }, 500);
        restaurantName.setText(restaurant.getName());
        restaurantDescription.setText(restaurant.getDescription());
        openHour.setText(restaurant.getOpenHour()==null ? "--:--":restaurant.getOpenHour().toString());
        closeHour.setText(restaurant.getCloseHour()==null ? "--:--":restaurant.getCloseHour().toString());
        restaurantAddress.setText(restaurant.getAddress());
        restaurantPhone.setText(restaurant.getPhone());
        restaurantEmail.setText(restaurant.getEmail());
        restaurantWebsite.setText(restaurant.getWebsite());

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
                }
            }
        });

        restaurantImage.setOnClickListener(v -> {
            showInputImageURLDialog();
        });

        saveChanges.setOnClickListener(v -> {
            if (checkFields()) {
                String name = restaurantName.getText().toString();
                String open = openHour.getText().toString();
                String close = closeHour.getText().toString();
                String address = restaurantAddress.getText().toString();
                String phone = restaurantPhone.getText().toString();
                String email = restaurantEmail.getText().toString();
                String website = restaurantWebsite.getText().toString();
                String description = restaurantDescription.getText().toString();
                Restaurant editRestaurant = new Restaurant(name, open, close, address, phone, email, website, description);
                editRestaurant.setId(restaurant.getId());
                if (restaurant.getImage()!=null)
                    editRestaurant.setImage(restaurant.getImage());
                if (editRestaurant.equals(restaurant))
                    Toast.makeText(getContext(), "No Changes Detected!", Toast.LENGTH_SHORT).show();
                else {
                    VendorController.generateNewRestaurantRequest(restaurant, editRestaurant);
                    Toast.makeText(getContext(), "Claim Request Submitted", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
            else{
                if (!checkTime(openHour.getText().toString()) || !checkTime(closeHour.getText().toString()))
                    Toast.makeText(getContext(), "Invalid time!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Missing fields!", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> dismiss());

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
    }

    private void showInputImageURLDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        builder.setTitle("Enter ImageURL")
                .setPositiveButton("Submit", (dialog, which) -> {
                    if (!input.getText().toString().isEmpty()) {
                        imageURL = input.getText().toString();
                        Glide.with(getContext()).load(imageURL).into(restaurantImage);
                    }
                    else
                        Toast.makeText(getContext(), "URL Empty", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setCancelable(false);
        builder.show();
    }
}
