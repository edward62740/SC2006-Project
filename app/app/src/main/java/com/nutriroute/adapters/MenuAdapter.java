package com.nutriroute.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nutriroute.R;
import com.nutriroute.controllers.UserController;
import com.nutriroute.enums.MealType;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.Restaurant;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<MenuItem> menuItems;
    private Restaurant restaurant;
    private Context context;
    static ImageView imageView;

    public MenuAdapter(Restaurant restaurant, Context context) {
        this.restaurant = restaurant;
        this.menuItems = restaurant.getMenu().getItems();
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        if (menuItem == null) {
            return;
        }
        holder.textName.setText(menuItem.getName());
        holder.textDescription.setText(menuItem.getDescription());
        holder.textPrice.setText("$" + menuItem.getPrice());
        holder.textCalories.setText(menuItem.getCalories() + " cal");
        // Set OnClickListener on the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMealAndAlloc(menuItem);
            }
        });
        if (menuItem.getImage() != null)
        {
            Glide.with(context).load(menuItem.getImage()).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showImageDialog(menuItem.getImage());
                }
            });
        }
        else // load the empty image/no image available
        {
            Glide.with(context).load(R.drawable.no_image_available).into(imageView);
        }
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
        return menuItems.size();
    }

    @Override
    public int getItemViewType(int position) {return position;}

    @Override
    public long getItemId(int position) {return Integer.toUnsignedLong(position);}

    // Show a dialog to prompt the user for the meal type
    private void selectMealAndAlloc(MenuItem menuItem) {
        String[] mealOptions = {"Breakfast", "Lunch", "Dinner", "Misc"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which meal are you having this for?");

        builder.setItems(mealOptions, (dialog, which) -> {
            MealType selectedMealType;
            switch (which) {
                case 0:
                    selectedMealType = MealType.BREAKFAST;
                    break;
                case 1:
                    selectedMealType = MealType.LUNCH;
                    break;
                case 2:
                    selectedMealType = MealType.DINNER;
                    break;
                case 3:
                default:
                    selectedMealType = MealType.MISC;
                    break;
            }
            Toast.makeText(context, "Recorded: " + menuItem.getName() + " for " + selectedMealType, Toast.LENGTH_SHORT).show();
            UserController.updateCalories(restaurant.getName(), menuItem.getName(), menuItem.getCalories(), selectedMealType);
        });

        // Show the dialog
        builder.show();
    }


    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textDescription, textPrice, textCalories;

        public MenuViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            textPrice = itemView.findViewById(R.id.text_price);
            textCalories = itemView.findViewById(R.id.text_calories);
            imageView = itemView.findViewById(R.id.menu_item_image);
        }
    }
}
