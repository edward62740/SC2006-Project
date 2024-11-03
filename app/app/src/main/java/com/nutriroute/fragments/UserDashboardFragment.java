package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.CalorieHistoryAdapter;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the fragment which contains the "Dashboard".
 * It provides some high-level logic, and delegates the rest to the adapter.
 */
public class UserDashboardFragment extends Fragment {
    private RecyclerView calorieHistoryRecyclerView;
    private TextView textCaloriesDay, textCaloriesCount;
    CalorieHistoryAdapter calorieHistoryAdapter;
    private List<CalorieDay> calorieDays;
    private int currentDayIndex = 0; // Track the current day being displayed

    User currentUser = ((User) AuthStore.getCurUser());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_dashboard, container, false);

        // Initialize views
        textCaloriesDay = view.findViewById(R.id.text_calories_day);
        textCaloriesCount = view.findViewById(R.id.text_calories_count);
        ImageView buttonBack = view.findViewById(R.id.button_back);
        ImageView buttonForward = view.findViewById(R.id.button_forward);
        calorieHistoryRecyclerView = view.findViewById(R.id.user_calorie_history);

        calorieDays = currentUser.getCaloriesHistory();
        currentDayIndex = calorieDays.size() ; // Start with the most recent day

        // Display today's calories initially
        updateCalorieText(currentDayIndex);

        // Set up back button listener
        buttonBack.setOnClickListener(v -> {
            if (currentDayIndex > 0) {
                currentDayIndex--; // Move to the previous day
                updateCalorieText(currentDayIndex);
            }
        });

        // Set up forward button listener
        buttonForward.setOnClickListener(v -> {
            if (currentDayIndex <= calorieDays.size() - 1) {
                currentDayIndex++; // Move to the next day
                updateCalorieText(currentDayIndex);
            }
        });
        // Setup RecyclerView for calorie history

        calorieHistoryRecyclerView = view.findViewById(R.id.user_calorie_history);
        calorieHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the list of CalorieDay and initialize the adapter
        List<CalorieDay> calorieDays = currentUser.getCaloriesHistory();
        //check if today already appended
        if (!calorieDays.contains(currentUser.getCaloriesToday())) {
            calorieDays.add(currentUser.getCaloriesToday());
        }
        calorieHistoryAdapter = new CalorieHistoryAdapter(calorieDays);
        calorieHistoryRecyclerView.setAdapter(calorieHistoryAdapter);
        return view;
    }

    // Method to update only the calorie TextView based on the day index
    @SuppressLint("SetTextI18n")
    private void updateCalorieText(int dayIndex) {

        // Get the calorie data for the selected day
        if (dayIndex >= calorieDays.size() ) {
            textCaloriesCount.setText("Calories: " +  currentUser.getCaloriesToday().getTotalCalories());
            textCaloriesDay.setText("Today"); // expected because state is updated
            return;
        }
        CalorieDay selectedDay = calorieDays.get(dayIndex);
        calorieHistoryRecyclerView.smoothScrollToPosition(calorieDays.size()  - dayIndex);


        // Update the TextView with the selected day's calorie count
        textCaloriesCount.setText("Calories: " + selectedDay.getTotalCalories());
        textCaloriesDay.setText(selectedDay.getDate().toString());
    }
}
