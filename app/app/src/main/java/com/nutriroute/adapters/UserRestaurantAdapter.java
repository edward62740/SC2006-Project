package com.nutriroute.adapters;

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
    RecyclerView menuRecyclerView;

    public UserRestaurantAdapter(Context context, Pair<List<Restaurant>, List<Float>> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList.first;
        this.distanceList = restaurantList.second;
    }

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

    private void showRestaurantDialog(Restaurant restaurant) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_restaurant_info, null);

        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        TextView dialogAddress = dialogView.findViewById(R.id.dialog_address);
        TextView dialogPhone = dialogView.findViewById(R.id.dialog_phone);
        TextView dialogDescription = dialogView.findViewById(R.id.dialog_description);
        WebView mapWebView = dialogView.findViewById(R.id.map_webview);
        mapWebView.getSettings().setJavaScriptEnabled(true);
        Button dialogOkButton = dialogView.findViewById(R.id.dialog_ok_button);

        menuRecyclerView = dialogView.findViewById(R.id.restaurant_menu);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Load the restaurant menu items
        MenuAdapter menuAdapter = new MenuAdapter(restaurant.getMenu().getItems(), context);
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
        url[0] += "&marker=postalcode:" + restaurant.getAddress() + "!colour:red!rType:TRANSIT!rDest:" + restaurant.getLocation()
                + "&marker=postalcode:" + restaurant.getAddress() + "!colour:red!rType:TRANSIT!rDest:" + UserStoresFragment.getUserLatLong();

        mapWebView.loadUrl(url[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogOkButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
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
