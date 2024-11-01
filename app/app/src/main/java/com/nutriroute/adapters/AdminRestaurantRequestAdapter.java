package com.nutriroute.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.enums.RequestType;
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
        if (request.getType()==RequestType.CLAIM_REQUEST)
            holder.textReason.setText("Request Type: Restaurant Claim");
        else
            holder.textReason.setText("Request Type: Update Restaurant Details");

        holder.itemView.setOnClickListener(v -> {
            showDetailDialog(request, position);
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


    private void showDetailDialog(RestaurantRequest request, int position) {

        String requestType = (request.getType()==RequestType.CLAIM_REQUEST ? "Restaurant Claim" : "Update Restaurant Details");
        String message =
                "Restaurant ID: " + request.getRestaurantId() +
                        "\nVendor ID: " + request.getVendorId() +
                        "\nRequest Type: " + requestType;

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        builder.setTitle(requestType)
                .setMessage(message)
                .setView(input)
                .setPositiveButton("Accept", (dialog, which) -> {
                    AdminController.acceptRequest(request);
                    //AdminController.deleteRequest(request.getId());
                    // reload adapter
                    restaurantRequestList.remove(position);
                    notifyItemRemoved(position);
                    restaurantRequestList.remove(request);
                    notifyItemRangeChanged(position, restaurantRequestList.size());
                })
                .setNegativeButton("Reject", (dialog, which) -> {
                    AdminController.rejectRequest(request, input.getText().toString());
                    //AdminController.deleteRequest(request.getId());
                    // reload adapter
                    restaurantRequestList.remove(position);
                    notifyItemRemoved(position);
                    restaurantRequestList.remove(request);
                    notifyItemRangeChanged(position, restaurantRequestList.size());
                })
                .setNeutralButton("Exit", (dialog, which) -> {
                    dialog.dismiss();
                });
        input.setHint("Reason");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
