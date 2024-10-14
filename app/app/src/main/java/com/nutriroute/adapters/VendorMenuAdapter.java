package com.nutriroute.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;

import java.text.NumberFormat;
import java.util.List;

public class VendorMenuAdapter extends RecyclerView.Adapter<VendorMenuAdapter.MenuViewHolder> {

    private Menu menu;
    private List<MenuItem> menuItems;
    private Context context;

    public VendorMenuAdapter(Context context, Menu menu){
        this.context = context;
        this.menu = menu;
        this.menuItems = menu.getItems();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vendor_item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = this.menu.get(position);
        if (item == null) {return;}

        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText("$" + item.getPrice());
        holder.itemCalories.setText(Integer.toString(item.getCalories()));
        // set EditText to update menuItem when text change has been detected
        holder.itemCalories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (holder.itemCalories.getText().toString().isEmpty())
                    item.setCalories(0);
                else
                    item.setCalories(Integer.parseInt(holder.itemCalories.getText().toString()));
            }
        });
        holder.itemDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                item.setDescription(holder.itemDescription.getText().toString());
            }
        });
        holder.itemPrice.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                // preserve the format of currency
                if(!s.toString().equals(current)) {
                    holder.itemPrice.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    current = formatted;
                    holder.itemPrice.setText(formatted);
                    holder.itemPrice.setSelection(formatted.length());

                    holder.itemPrice.addTextChangedListener(this);
                    item.setPrice(parsed/100);
                }
            }
        });

        // TODO: probably changing this part to modify a request directly
        // Parse a new request from dialog onto here and modify
        // then check for changes in dialog when submit is pressed

        holder.itemImage.setOnClickListener(v -> {
            //TODO: Implement logic to upload image
        });

    }


    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    @Override
    public int getItemViewType(int position) {return position;}

    @Override
    public long getItemId(int position) {return Integer.toUnsignedLong(position);}

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public ImageButton itemImage;
        public TextView itemName;
        public EditText itemDescription, itemPrice, itemCalories;

        public MenuViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.menu_item_image);
            itemName = itemView.findViewById(R.id.text_name);
            itemDescription = itemView.findViewById(R.id.text_description);
            itemPrice = itemView.findViewById(R.id.text_price);
            itemCalories = itemView.findViewById(R.id.text_calories);

        }
    }
}