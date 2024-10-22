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
import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.RestaurantRequest;

import java.util.List;

public class AdminRestaurantRequestAdapter extends RecyclerView.Adapter<AdminRestaurantRequestAdapter.ViewHolder> {

    private List<Request<String>> restaurantRequestList;
    private Context context;

    public AdminRestaurantRequestAdapter(Context context, List<Request<String>> restaurantRequestList) {
        this.context = context;
        this.restaurantRequestList = restaurantRequestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RestaurantRequest request = (RestaurantRequest) restaurantRequestList.get(position);
        holder.textRestaurantId.setText("Restaurant ID: " + request.getRestaurantId());
        holder.textVendorId.setText("Vendor ID: " + request.getVendorId());
        holder.textReason.setText("Claim reason: "+ request.getReason());

        holder.itemView.setOnClickListener(v -> {
            showDetailDialog(request);
        });
    }

    @Override
    public int getItemCount() {
        return restaurantRequestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textRestaurantId;
        TextView textVendorId;
        TextView textReason;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textRestaurantId = itemView.findViewById(R.id.textRestaurantId);
            textVendorId = itemView.findViewById(R.id.textVendorId);
            textReason = itemView.findViewById(R.id.textReason);
        }
    }


    private void showDetailDialog(RestaurantRequest request) {

        String message =
                "Restaurant ID: " + request.getRestaurantId() +
                "\nVendor ID: " + request.getVendorId() +
                "\nChange Type: " + request.getChangeType() +
                "\nReason: " + request.getReason();

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Restaurant Claim Details")
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
