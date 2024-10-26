package com.nutriroute.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.UserController;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.R;
import java.util.List;

public class AdminMenuUpdateAdapter extends RecyclerView.Adapter<AdminMenuUpdateAdapter.ViewHolder> {

    private List<Request<String>> menuRequestList;
    private Context context;

    public AdminMenuUpdateAdapter(Context context, List<Request<String>> menuRequestList) {
        this.context = context;
        this.menuRequestList = menuRequestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuRequest request = (MenuRequest)  menuRequestList.get(position);
        holder.textMenuItemId.setText("Menu item ID: " + request.getMenuItemID());
        holder.textRestaurantId.setText("Restaurant ID: " + request.getRestaurantId());
        holder.textVendorId.setText("Vendor ID: " + request.getVendorId());
        holder.textChangeType.setText("Change Type: " + request.getChangeType());

        holder.itemView.setOnClickListener(v -> {
            showDetailDialog(request);
        });
    }

    @Override
    public int getItemCount() {
        return menuRequestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMenuItemId;
        TextView textRestaurantId;
        TextView textVendorId;
        TextView textChangeType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMenuItemId = itemView.findViewById(R.id.textMenuItemId);
            textRestaurantId = itemView.findViewById(R.id.textRestaurantId);
            textVendorId = itemView.findViewById(R.id.textVendorId);
            textChangeType = itemView.findViewById(R.id.textChangeType);
        }
    }

    private void showDetailDialog(MenuRequest request) {

        String message = "Menu Item ID: " + request.getMenuItemID() +
                "\nRestaurant ID: " + request.getRestaurantId() +
                "\nVendor ID: " + request.getVendorId() +
                "\nChange Type: " + request.getChangeType() +
                "\nReason: " + request.getReason();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Menu Request Details")
                .setMessage(message)
                .setPositiveButton("Accept", (dialog, which) -> {

                    AdminController.acceptRequest(request);
                })
                .setNegativeButton("Reject", (dialog, which) -> {
                    AdminController.rejectRequest(request);
                })
                .setNeutralButton("Exit", (dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
