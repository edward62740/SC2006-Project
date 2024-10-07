package com.nutriroute.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.fragments.UserStoresFragment;
import com.nutriroute.models.Restaurant;
import com.nutriroute.utils.PostalCodeHelper;

import java.util.List;

public class UserRestaurantAdapter extends RecyclerView.Adapter<UserRestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private List<Float> distanceList;
    private Context context;
    PostalCodeHelper postalCodeHelper = new PostalCodeHelper();

    public UserRestaurantAdapter(Context context, Pair<List<Restaurant>, List<Float>> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList.first;
        this.distanceList = restaurantList.second;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(Pair<List<Restaurant>, List<Float>> restaurantList) {
        this.restaurantList = restaurantList.first;
        this.distanceList = restaurantList.second;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
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
        holder.textDistance.setText("Distance: " + distanceList.get(position) + " km");

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> showRestaurantDialog(restaurant));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void showRestaurantDialog(Restaurant restaurant) {
        com.nutriroute.adapters.RestaurantDialogFragment dialogFragment = new com.nutriroute.adapters.RestaurantDialogFragment(restaurant);
        dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "RestaurantDialog");
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textAddress, textPhone, textDescription, textDistance;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textAddress = itemView.findViewById(R.id.text_address);
            textPhone = itemView.findViewById(R.id.text_phone);
            textDescription = itemView.findViewById(R.id.text_description);
            textDistance = itemView.findViewById(R.id.text_dist);
        }
    }
}
