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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nutriroute.R;
import com.nutriroute.fragments.UserStoresFragment;
import com.nutriroute.models.Restaurant;
import com.nutriroute.utils.PostalCodeHelper;

import java.util.List;

public class UserRestaurantAdapter extends RecyclerView.Adapter<UserRestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private List<Float> distanceList;
    private Context context;
    static ImageView imageView;
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
        if (restaurant.getImage() != null)
        {
            Glide.with(context).load(restaurant.getImage()).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showImageDialog(restaurant.getImage());
                }
            });
        }
        else // load the empty image/no image available
        {
            Glide.with(context).load(R.drawable.no_image_available).into(imageView);
        }

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> showRestaurantDialog(restaurant));
    }
    private void showImageDialog(String imageUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_image, null);
        ImageView enlargedImageView = dialogView.findViewById(R.id.enlarged_image);

        Glide.with(context).load(imageUrl).into(enlargedImageView);

        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.show();
    }
    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @Override
    public int getItemViewType(int position) {return position;}

    @Override
    public long getItemId(int position) {return Integer.toUnsignedLong(position);}

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
            imageView = itemView.findViewById(R.id.image_restaurant);
        }
    }
}
