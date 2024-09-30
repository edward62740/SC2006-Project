package com.nutriroute.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.models.Restaurant;
import com.nutriroute.utils.PostalCodeHelper;
//import com.bumptech.glide.Glide;

import java.util.List;

public class UserRestaurantAdapter extends RecyclerView.Adapter<UserRestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private Context context; // Store context for dialog creation
    PostalCodeHelper postalCodeHelper = new PostalCodeHelper();

    public UserRestaurantAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context; // Initialize context
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        postalCodeHelper.fromPostalCodeGetAddress(Integer.parseInt(restaurant.getAddress()), address -> {
            if (address != null) {
                holder.textAddress.setText("Address: " + address);
            } else {
                holder.textAddress.setText("Invalid address");
            }
        });

        holder.textName.setText(restaurant.getName());
        holder.textPhone.setText("Phone: " + restaurant.getPhone());
        holder.textDescription.setText(restaurant.getDescription());

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> showRestaurantDialog(restaurant));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    private void showRestaurantDialog(Restaurant restaurant) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_restaurant_info, null);

        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        TextView dialogAddress = dialogView.findViewById(R.id.dialog_address);
        TextView dialogPhone = dialogView.findViewById(R.id.dialog_phone);
        TextView dialogDescription = dialogView.findViewById(R.id.dialog_description);
        Button dialogOkButton = dialogView.findViewById(R.id.dialog_ok_button);


        dialogTitle.setText(restaurant.getName());
        dialogAddress.setText("Address: " + restaurant.getAddress());
        dialogPhone.setText("Phone: " + restaurant.getPhone());
        dialogDescription.setText("Description: " + restaurant.getDescription());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();


        dialogOkButton.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textAddress, textPhone, textDescription;
        public ImageView imageRestaurant;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textAddress = itemView.findViewById(R.id.text_address);
            textPhone = itemView.findViewById(R.id.text_phone);
            textDescription = itemView.findViewById(R.id.text_description);
            imageRestaurant = itemView.findViewById(R.id.image_restaurant);
        }
    }
}
