package com.nutriroute.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;

import java.util.List;

//TODO: Implement claims recyclerView
public class VendorClaimsAdapter extends RecyclerView.Adapter<VendorClaimsAdapter.ClaimsViewHolder>{

    private Context context;
    private List<Request> requestList;

    public VendorClaimsAdapter(Context context, List<Request> requestList){
        this.context = context;
        this.requestList = requestList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Request> requestList) {
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
        System.out.println("TEST");
    }

    public static class ClaimsViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textDescription;
        public Button buttonView;

        public ClaimsViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            buttonView = itemView.findViewById(R.id.button_view);

        }
    }
}
