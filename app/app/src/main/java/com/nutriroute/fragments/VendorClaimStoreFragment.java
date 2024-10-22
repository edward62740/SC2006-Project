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
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;

public class VendorClaimStoreFragment extends Fragment {
    private ImageButton backButton;
    private TextView textTitle;
    private EditText restaurantName, openHour, closeHour;
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
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new VendorStoresFragment()).commit();
            Toast.makeText(getContext(), "Claim Request Submitted", Toast.LENGTH_SHORT).show();
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
        if (minute > 59)
            minute=59;
        return (hour<10 ? "0" + hour : hour) + ":" + (minute<10 ? "0" + minute : minute);
    }
}
