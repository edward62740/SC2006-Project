package com.nutriroute.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;

import java.util.List;

public class VendorClaimsAdapter extends RecyclerView.Adapter<VendorClaimsAdapter.ClaimsViewHolder>{

    private Context context;
    private List<Request<String>> requestList;

    public VendorClaimsAdapter(Context context, List<Request<String>> requestList){
        this.context = context;
        this.requestList = requestList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Request<String>> requestList) {
        this.requestList = requestList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VendorClaimsAdapter.ClaimsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vendor_item_claims, parent, false);
        return new VendorClaimsAdapter.ClaimsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VendorClaimsAdapter.ClaimsViewHolder holder, int position) {
        if (requestList.get(position)==null) return;

        RequestType type = requestList.get(position).getType();
        Request<String> request = requestList.get(position);

        holder.textName.setText(request.getId());
        if(request.getStatus() == RequestStatus.APPROVED)
            holder.textName.setTextColor(context.getResources().getColor(R.color.green));
        else if (request.getStatus() == RequestStatus.DENIED)
            holder.textName.setTextColor(context.getResources().getColor(R.color.red));
        else if (request.getStatus() == RequestStatus.PENDING)
            holder.textName.setTextColor(context.getResources().getColor(R.color.teal_700));
        holder.textDescription.setText(request.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (type == RequestType.CLAIM_REQUEST) {
                RestaurantRequest claimRequest = (RestaurantRequest) requestList.get(position);
                showDetailDialog(claimRequest, RequestType.CLAIM_REQUEST);
            } else if (type == RequestType.RESTAURANT_CHANGE_REQUEST){
                RestaurantRequest restaurantRequest = (RestaurantRequest) requestList.get(position);
                showDetailDialog(restaurantRequest, RequestType.RESTAURANT_CHANGE_REQUEST);
            } else {
                MenuRequest menuRequest = (MenuRequest) requestList.get(position);
                showDetailDialog(menuRequest);
            }
            if (request.getStatus() == RequestStatus.APPROVED || request.getStatus() == RequestStatus.DENIED){
                VendorController.deleteRequest(request.getId());
                // reload adapter
                requestList.remove(position);
                notifyItemRemoved(position);
                requestList.remove(request);
                notifyItemRangeChanged(position, requestList.size());

            }
        });
    }

    public static class ClaimsViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textDescription;
        public Button buttonView;

        public ClaimsViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);

        }
    }

    //Todo: Improve Dialog UI

    private void showDetailDialog(MenuRequest request) {

        String message =
                "Vendor ID: " + request.getVendorId() +
                        "\nRestaurant ID: " + request.getRestaurantId() +
                        "\nMenu Item Name: " + request.getNewValue().getName() +
                        "\nChange Type: " + request.getChangeType() +
                        "\nStatus: " + request.getStatus() +
                        "\nReason: " + request.getReason();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Menu Request Details")
                .setMessage(message)
                .setNeutralButton("Exit", (dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDetailDialog(RestaurantRequest request, RequestType requestType) {
        String message, title;
        if (requestType==RequestType.RESTAURANT_CHANGE_REQUEST) {
            message =
                    "Vendor ID: " + request.getVendorId() +
                            "\nRestaurant ID: " + request.getRestaurantId() +
                            "\nStatus: " + request.getStatus() +
                            "\nReason: " + request.getReason();
            title = "Change Restaurant Details";
        }
        else{
            message =
                    "\nVendor ID: " + request.getVendorId() +
                            "\nRestaurant ID: " + request.getRestaurantId() +
                            "\nStatus: " + request.getStatus() +
                            "\nReason: " + request.getReason();
            title = "Restaurant Claim Details";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setNeutralButton("Exit", (dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
