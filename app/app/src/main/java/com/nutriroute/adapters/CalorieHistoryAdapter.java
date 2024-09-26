package com.nutriroute.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.models.CalorieDay;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CalorieHistoryAdapter extends RecyclerView.Adapter<CalorieHistoryAdapter.CalorieDayViewHolder> {

    private List<CalorieDay> calorieDays;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public CalorieHistoryAdapter(List<CalorieDay> calorieDays) {
        this.calorieDays = calorieDays;
    }

    @NonNull
    @Override
    public CalorieDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calorie_day, parent, false);
        return new CalorieDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalorieDayViewHolder holder, int position) {
        CalorieDay calorieDay = calorieDays.get(position);
        System.out.println(calorieDay.getDate());

        // Set the date header
        holder.dateTextView.setText("Date: " + calorieDay.getDate());

        // Clear previous entries
        holder.calorieEntriesLayout.removeAllViews();

        // Create views for each food entry
        for (int i = 0; i < calorieDay.getFoodConsumed().size(); i++) {
            View calorieEntryView = LayoutInflater.from(holder.itemView.getContext())
                    .inflate(R.layout.calorie_entry, holder.calorieEntriesLayout, false);

            TextView restaurantTextView = calorieEntryView.findViewById(R.id.text_restaurant);
            TextView foodTextView = calorieEntryView.findViewById(R.id.text_menu_item);
            TextView caloriesTextView = calorieEntryView.findViewById(R.id.text_calories);

            restaurantTextView.setText(calorieDay.getFoodRestaurant().get(i));
            foodTextView.setText(calorieDay.getFoodConsumed().get(i));
            caloriesTextView.setText(calorieDay.getCaloriesConsumed().get(i) + " kcal");

            holder.calorieEntriesLayout.addView(calorieEntryView);
        }
    }

    @Override
    public int getItemCount() {
        return calorieDays.size();
    }

    static class CalorieDayViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        LinearLayout calorieEntriesLayout; // Added layout to hold entries

        public CalorieDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_date);
            calorieEntriesLayout = itemView.findViewById(R.id.calorie_entries); // Ensure this is defined in item_calorie_day.xml
        }
    }
}
