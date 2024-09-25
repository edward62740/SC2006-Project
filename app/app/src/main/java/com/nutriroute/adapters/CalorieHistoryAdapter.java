package com.nutriroute.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        // Set date
        holder.dateTextView.setText("Date: " + dateFormat.format(calorieDay.getDate()));

        // Concatenate calories consumed for the day
        List<Integer> calories = calorieDay.getCaloriesConsumed();
        StringBuilder caloriesString = new StringBuilder("Calories: ");
        for (int calorie : calories) {
            caloriesString.append(calorie).append(" kcal, ");
        }
        holder.caloriesTextView.setText(caloriesString.toString());

        // Concatenate food items
        List<String> foods = calorieDay.getFoodConsumed();
        StringBuilder foodString = new StringBuilder("Foods: ");
        for (String food : foods) {
            foodString.append(food).append(", ");
        }
        holder.foodTextView.setText(foodString.toString());

        // Concatenate restaurant IDs
        List<String> restaurants = calorieDay.getFoodRestaurant();
        StringBuilder restaurantString = new StringBuilder("Restaurants: ");
        for (String restaurant : restaurants) {
            restaurantString.append(restaurant).append(", ");
        }
        holder.restaurantTextView.setText(restaurantString.toString());
    }

    @Override
    public int getItemCount() {
        return calorieDays.size();
    }

    static class CalorieDayViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView caloriesTextView;
        TextView foodTextView;
        TextView restaurantTextView;

        public CalorieDayViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.text_date);
            caloriesTextView = itemView.findViewById(R.id.text_calories);
            foodTextView = itemView.findViewById(R.id.text_food);
            restaurantTextView = itemView.findViewById(R.id.text_restaurant);
        }
    }
}
