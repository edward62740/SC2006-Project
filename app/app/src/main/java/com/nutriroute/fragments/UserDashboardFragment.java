package com.nutriroute.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nutriroute.R;
import com.nutriroute.adapters.CalorieHistoryAdapter;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class UserDashboardFragment extends Fragment {
    private RecyclerView calorieHistoryRecyclerView;
    private CalorieHistoryAdapter calorieHistoryAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_fragment_dashboard, container, false);
        // set textview
        TextView text_today_calories = view.findViewById(R.id.text_today_calories);
        System.out.println(((User) AuthStore.getCurUser()).getTodayCalories());
        text_today_calories.setText("Today's Calories: " +  ((User) AuthStore.getCurUser()).getTodayCalories());
        calorieHistoryRecyclerView = view.findViewById(R.id.user_calorie_history);
        calorieHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Assuming AuthStore.getCurUser().getCalorieDays() returns the list of CalorieDay
        List<CalorieDay> calorieDays = ((User) AuthStore.getCurUser()).getCaloriesHistory();
        System.out.println(calorieDays);

        calorieHistoryAdapter = new CalorieHistoryAdapter(calorieDays);
        calorieHistoryRecyclerView.setAdapter(calorieHistoryAdapter);

        return view;
    }
}