package com.nutriroute.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nutriroute.R;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class VendorMenuAdapter extends RecyclerView.Adapter<VendorMenuAdapter.MenuViewHolder> {

    private Menu menu;
    private List<MenuItem> menuItems;
    private Context context;
    List<MenuItem> editItemList;

    public VendorMenuAdapter(Context context, Menu menu, List<MenuItem> editItemList){
        this.context = context;
        this.menu = menu;
        this.menuItems = menu.getItems();
        this.editItemList = editItemList;
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
        MenuItem editItem = editItemList.get(position);
        if (item == null) {return;}

        if (item.getImage()!=null) {
            editItem.setImage(item.getImage());
            Glide.with(context).load(item.getImage()).into(holder.itemImage);
        }
        else
            Glide.with(context).load(R.drawable.no_image_available).into(holder.itemImage);

        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText("$" + item.getPrice());
        holder.itemCalories.setText(Integer.toString(item.getCalories()));
        // set EditText to update menuItem when text change has been detected
        holder.itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editItem.setName(holder.itemName.getText().toString());
            }
        });
        holder.itemCalories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (holder.itemCalories.getText().toString().isEmpty())
                    editItem.setCalories(0);
                else
                    editItem.setCalories(Integer.parseInt(holder.itemCalories.getText().toString()));
            }
        });
        holder.itemDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editItem.setDescription(holder.itemDescription.getText().toString());
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
                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                    format.setMaximumFractionDigits(2);
                    String formatted = format.format((parsed / 100));

                    current = formatted;
                    holder.itemPrice.setText(formatted);
                    holder.itemPrice.setSelection(formatted.length());

                    holder.itemPrice.addTextChangedListener(this);
                    editItem.setPrice(parsed/100);
                }
            }
        });

        holder.itemImage.setOnClickListener(v -> {
            showInputImageURLDialog(holder, editItem);
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

    private void showInputImageURLDialog(MenuViewHolder holder, MenuItem editItem){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        builder.setTitle("Enter ImageURL")
                .setPositiveButton("Submit", (dialog, which) -> {
                    if (!input.getText().toString().isEmpty()) {
                        editItem.setImage(input.getText().toString());
                        Glide.with(context).load(input.getText().toString()).into(holder.itemImage);
                    }
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