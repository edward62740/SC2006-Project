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

import com.nutriroute.controllers.AdminController;
import com.nutriroute.controllers.UserController;
import com.nutriroute.controllers.VendorController;
import com.nutriroute.enums.RequestStatus;
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
        holder.textMenuItemId.setText("Menu item ID: " + request.getNewValue().getName());
        holder.textRestaurantId.setText("Restaurant ID: " + request.getRestaurantId());
        holder.textVendorId.setText("Vendor ID: " + request.getVendorId());
        holder.textChangeType.setText("Change Type: " + request.getChangeType());

        holder.itemView.setOnClickListener(v -> {
            showDetailDialog(request, position);
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

    private void showDetailDialog(MenuRequest request, int position) {

        String message = "Menu Item Name: " + request.getNewValue().getName() +
                "\nRestaurant ID: " + request.getRestaurantId() +
                "\nVendor ID: " + request.getVendorId() +
                "\nChange Type: " + request.getChangeType() +
                "\nReason: " + request.getReason();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        builder.setTitle("Menu Request Details")
                .setMessage(message)
                .setView(input)
                .setPositiveButton("Accept", (dialog, which) -> {
                    AdminController.acceptRequest(request);
                    //AdminController.deleteRequest(request.getId());
                    // reload adapter
                    menuRequestList.remove(position);
                    notifyItemRemoved(position);
                    menuRequestList.remove(request);
                    notifyItemRangeChanged(position, menuRequestList.size());
                })
                .setNegativeButton("Reject", (dialog, which) -> {
                    AdminController.rejectRequest(request, input.getText().toString());
                    //AdminController.deleteRequest(request.getId());
                    // reload adapter
                    menuRequestList.remove(position);
                    notifyItemRemoved(position);
                    menuRequestList.remove(request);
                    notifyItemRangeChanged(position, menuRequestList.size());
                })
                .setNeutralButton("Exit", (dialog, which) -> {
                    dialog.dismiss();
                });
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Reason");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
