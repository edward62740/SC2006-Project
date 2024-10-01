package com.nutriroute.adapters;

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
import com.nutriroute.models.MenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<MenuItem> menuItems;
    private Context context;
    static ImageView imageView;

    public MenuAdapter(List<MenuItem> menuItems, Context context) {
        this.menuItems = menuItems;
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
                // Show toast with the menu item name
                Toast.makeText(context, "Clicked, TODO impl 1-6: " + menuItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context).load("https://lh3.googleusercontent.com/p/AF1QipNMwMB3ZR2p2Nz61uCrCgzv2-dw3in2kIM5SfAd=s680-w680-h510").into(imageView);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
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
