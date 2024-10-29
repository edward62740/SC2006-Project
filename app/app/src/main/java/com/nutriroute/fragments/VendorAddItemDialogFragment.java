package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.enums.RequestType;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.Restaurant;

import java.text.NumberFormat;
import java.util.Locale;

public class VendorAddItemDialogFragment extends DialogFragment {

    private Restaurant restaurant;
    private Menu menu;
    private RecyclerView menuRecyclerView;
    ImageButton itemImage, backButton;
    EditText itemName, itemPrice, itemCalories, itemDescription;
    Button submitButton;
    String imageURL;

    public VendorAddItemDialogFragment(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.menu = restaurant.getMenu();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_item, container, false);

        itemImage = view.findViewById(R.id.button_image);
        itemName = view.findViewById(R.id.text_name);
        itemPrice = view.findViewById(R.id.text_price);
        itemCalories = view.findViewById(R.id.text_calories);
        itemDescription = view.findViewById(R.id.text_description);
        submitButton = view.findViewById(R.id.button_submit);
        backButton = view.findViewById(R.id.button_back);

        itemPrice.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                // preserve the format of currency
                if(!s.toString().equals(current)) {
                    itemPrice.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                    format.setMaximumFractionDigits(2);
                    String formatted = format.format((parsed / 100));

                    current = formatted;
                    itemPrice.setText(formatted);
                    itemPrice.setSelection(formatted.length());

                    itemPrice.addTextChangedListener(this);
                }
            }
        });

        itemImage.setOnClickListener(v -> {
            showInputImageURLDialog();
        });

        submitButton.setOnClickListener(v -> {
            if (checkFields()) {
                Menu menu = restaurant.getMenu();
                MenuItem menuItem = new MenuItem();
                menuItem.setName(itemName.getText().toString());
                double price = Double.valueOf(itemPrice.getText().toString().replace("$", ""));
                menuItem.setPrice(price);
                menuItem.setCalories(Integer.parseInt(itemCalories.getText().toString()));
                menuItem.setDescription(itemDescription.getText().toString());
                if (imageURL != null)
                    menuItem.setImage(imageURL);
                VendorController.generateNewMenuRequest(menu, menuItem);
                System.out.println("Add Item Request generated");
                Toast.makeText(getContext(), "Request Submitted", Toast.LENGTH_SHORT).show();
                dismiss();
            }
            else{
                Toast.makeText(getContext(), "Missing fields!", Toast.LENGTH_SHORT).show();
            }
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

    private Boolean checkFields() {
        return !itemName.getText().toString().isEmpty() &&
                !itemPrice.getText().toString().isEmpty() &&
                !itemCalories.getText().toString().isEmpty() &&
                !itemDescription.getText().toString().isEmpty();
        //todo add validation for proof image
    }

    private void showInputImageURLDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        builder.setTitle("Enter ImageURL")
                .setPositiveButton("Submit", (dialog, which) -> {
                    if (!input.getText().toString().isEmpty()) {
                        imageURL = input.getText().toString();
                        Glide.with(getContext()).load(imageURL).into(itemImage);
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
