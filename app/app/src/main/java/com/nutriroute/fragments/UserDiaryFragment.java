package com.nutriroute.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nutriroute.R;
import com.nutriroute.enums.MealType;
import com.nutriroute.interfaces.IUserCalorieManagementService;
import com.nutriroute.models.User;
import com.nutriroute.services.UserCalorieManagementService;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class UserDiaryFragment extends Fragment {

    private TextView tvTitle, tvCaloriesTitle, tvCalories, tvCaloriesRemaining,
            tvBreakfast, tvLunch, tvLunchDetails, tvLunchCalories, tvDinner,
            tvBreakfastDetails, tvBreakfastCalories, tvDinnerDetails, tvDinnerCalories;
    private ProgressBar caloriesProgressBar;
    private Button btnAddBreakfast, btnAddLunch, btnAddDinner;

    User currentUser = ((User) AuthStore.getCurUser());
    IUserCalorieManagementService calorieManagementService = new UserCalorieManagementService();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        tvTitle = view.findViewById(R.id.tvTitle);
        tvCaloriesTitle = view.findViewById(R.id.tvCaloriesTitle);
        tvCalories = view.findViewById(R.id.tvCalories);
        tvCaloriesRemaining = view.findViewById(R.id.tvCalories);
        tvBreakfast = view.findViewById(R.id.tvBreakfast);
        tvBreakfastDetails = view.findViewById(R.id.tvBreakfastDetails);
        tvBreakfastCalories = view.findViewById(R.id.tvBreakfastCalories);

        tvLunch = view.findViewById(R.id.tvLunch);
        tvLunchDetails = view.findViewById(R.id.tvLunchDetails);
        tvLunchCalories = view.findViewById(R.id.tvLunchCalories);

        tvDinnerDetails = view.findViewById(R.id.tvDinnerDetails);
        tvDinnerCalories = view.findViewById(R.id.tvDinnerCalories);
        tvDinner = view.findViewById(R.id.tvDinner);

        caloriesProgressBar = view.findViewById(R.id.caloriesProgressBar);


        btnAddBreakfast = view.findViewById(R.id.btnAddBreakfast);
        btnAddLunch = view.findViewById(R.id.btnAddLunch);
        btnAddDinner = view.findViewById(R.id.btnAddDinner);


        setupCaloriesProgress(currentUser.getCaloriesToday().getTotalCalories(),
                currentUser.getTargetCalories());

        showMealDetails();

        btnAddBreakfast.setOnClickListener(v -> addBreakfast());
        btnAddLunch.setOnClickListener(v -> addLunch());
        btnAddDinner.setOnClickListener(v -> addDinner());
    }

    private void setupCaloriesProgress(int progress, int max) {
        caloriesProgressBar.setMax(currentUser.getTargetCalories());
        caloriesProgressBar.setProgress(progress);
        tvCaloriesRemaining.setText(max-progress + " Remaining");
    }

    private void showMealDetails() {
        List<Integer> calories = currentUser.getCaloriesToday().getCaloriesConsumed();


        if(!calories.isEmpty() && calories.get(0) != null) {
            tvBreakfastDetails.setText("Details: " + currentUser.getCaloriesToday().getFoodConsumed().get(0));
            tvBreakfastCalories.setText("Calories: " + calories.get(0));
        }
        if(calories.size() > 1 && calories.get(1) != null) {
            tvLunchDetails.setText("Details: " + currentUser.getCaloriesToday().getFoodConsumed().get(1));
            tvLunchCalories.setText("Calories: " + calories.get(1));
        }
        if(calories.size() > 2 && calories.get(2) != null) {
            tvDinnerDetails.setText("Details: " + currentUser.getCaloriesToday().getFoodConsumed().get(2));
            tvDinnerCalories.setText("Calories: " + calories.get(2));
        }


    }

    private void addBreakfast() {
        showAddCalorieDialog(MealType.BREAKFAST);
    }

    private void addLunch() {
        showAddCalorieDialog(MealType.LUNCH);
    }

    private void addDinner() {
        showAddCalorieDialog(MealType.DINNER);
    }

    private void showAddCalorieDialog(MealType mealType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        // Inflate custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_calorie_item, null);
        builder.setView(dialogView);

        // Initialize dialog views
        EditText etRestaurantId = dialogView.findViewById(R.id.etRestaurantId);
        EditText etFoodId = dialogView.findViewById(R.id.etFoodId);
        EditText etCalories = dialogView.findViewById(R.id.etCalories);
        Spinner spinnerMealType = dialogView.findViewById(R.id.spinnerMealType);
        Button btnSubmit = dialogView.findViewById(R.id.btnSubmit);

        // Set up the MealType spinner
        ArrayAdapter<MealType> mealTypeAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, MealType.values());
        mealTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(mealTypeAdapter);

        // Set the initial value of the spinner to the passed mealType
        spinnerMealType.setSelection(mealTypeAdapter.getPosition(mealType));

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();


        btnSubmit.setOnClickListener(v -> {
            if (etRestaurantId.getText().toString().isEmpty() || etFoodId.getText().toString().isEmpty()
                    || etCalories.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String restaurantId = etRestaurantId.getText().toString();
            String foodId = etFoodId.getText().toString();
            int calories = Integer.parseInt(etCalories.getText().toString());

            MealType selectedMealType = (MealType) spinnerMealType.getSelectedItem();

            calorieManagementService.addCalorieItem(restaurantId, foodId, calories, selectedMealType);

            showMealDetails();
            setupCaloriesProgress(currentUser.getCaloriesToday().getTotalCalories(),
                    currentUser.getTargetCalories());
            dialog.dismiss();
        });
    }

}
