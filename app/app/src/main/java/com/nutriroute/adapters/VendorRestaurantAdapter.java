package com.nutriroute.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.fragments.VendorAddItemDialogFragment;
import com.nutriroute.fragments.VendorEditMenuDialogFragment;
import com.nutriroute.fragments.VendorEditRestaurantDialogFragment;
import com.nutriroute.models.Restaurant;

import java.util.List;

public class VendorRestaurantAdapter extends RecyclerView.Adapter<VendorRestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private Context context;

    public VendorRestaurantAdapter(Context context, List<Restaurant> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vendor_item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        if (restaurant==null)
            return;
        holder.textName.setText(restaurant.getName());
        holder.textPhone.setText("Phone: " + restaurant.getPhone());
        holder.textDescription.setText(restaurant.getDescription());
        holder.openingHour.setText(restaurant.getOpenHour()==null ? "--:--":restaurant.getOpenHour().toString());
        holder.closingHour.setText(restaurant.getCloseHour()==null ? "--:--":restaurant.getCloseHour().toString());
        // Set the click listener for the item view

        holder.buttonAddMenuItem.setOnClickListener(v -> showAddDialog(restaurant));
        holder.buttonEditMenu.setOnClickListener(v -> showEditMenuDialog(restaurant));
        holder.buttonEditRestaurant.setOnClickListener(v -> showEditRestaurantDialog(restaurant));

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void showEditMenuDialog(Restaurant restaurant) {
        VendorEditMenuDialogFragment dialogFragment = new VendorEditMenuDialogFragment(restaurant);
        dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "EditMenuDialog");
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void showAddDialog(Restaurant restaurant) {
        VendorAddItemDialogFragment dialogFragment = new VendorAddItemDialogFragment(restaurant);
        dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "AddMenuDialog");
    }

    private void showEditRestaurantDialog(Restaurant restaurant){
        VendorEditRestaurantDialogFragment dialogFragment = new VendorEditRestaurantDialogFragment(restaurant);
        dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "EditRestaurantDialog");
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textAddress, textPhone, textDescription, openingHour, closingHour;
        public ImageView buttonAddMenuItem, buttonEditMenu, buttonEditRestaurant;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textAddress = itemView.findViewById(R.id.text_address);
            textPhone = itemView.findViewById(R.id.text_phone);
            textDescription = itemView.findViewById(R.id.text_description);
            openingHour = itemView.findViewById(R.id.opening_hour);
            closingHour = itemView.findViewById(R.id.closing_hour);
            buttonAddMenuItem = itemView.findViewById(R.id.button_add_menu_item);
            buttonEditMenu = itemView.findViewById(R.id.button_edit_menu);
            buttonEditRestaurant = itemView.findViewById(R.id.button_edit_restaurant);
        }
    }
}