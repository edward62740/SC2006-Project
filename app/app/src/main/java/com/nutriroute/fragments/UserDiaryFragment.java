package com.nutriroute.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nutriroute.R;
import com.nutriroute.controllers.UserController;
import com.nutriroute.enums.MealType;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;

import java.util.List;

public class UserDiaryFragment extends Fragment {

    private TextView tvCaloriesRemaining;

    private ProgressBar caloriesProgressBar;
    private Button btnAddCalories;
    private ViewGroup miscMealsContainer, breakfastContainer, lunchContainer, dinnerContainer;

    User currentUser = ((User) AuthStore.getCurUser());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCaloriesRemaining = view.findViewById(R.id.tvCalories);
        miscMealsContainer = view.findViewById(R.id.miscMealContainer);
        breakfastContainer = view.findViewById(R.id.breakfastMealContainer);
        lunchContainer = view.findViewById(R.id.lunchMealContainer);
        dinnerContainer = view.findViewById(R.id.dinnerMealContainer);

        caloriesProgressBar = view.findViewById(R.id.caloriesProgressBar);

        btnAddCalories = view.findViewById(R.id.btnAddCalories);

        setupCaloriesProgress(currentUser.getCaloriesToday().getTotalCalories(),
                currentUser.getTargetCalories());

        showMealDetails();
        btnAddCalories.setOnClickListener(v -> addCalories());
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForColorStateLists"})
    private void setupCaloriesProgress(int progress, int max) {
        caloriesProgressBar.setMax(currentUser.getTargetCalories());
        caloriesProgressBar.setProgress(progress);
        tvCaloriesRemaining.setText(max-progress + " Remaining");
        if(progress > max) {
            // set color from hex #
            tvCaloriesRemaining.setTextColor(getResources().getColor(R.color.red));
            caloriesProgressBar.setProgressTintList(getResources().getColorStateList(R.color.red));
        } else {
            tvCaloriesRemaining.setTextColor(getResources().getColor(R.color.green));
            caloriesProgressBar.setProgressTintList(getResources().getColorStateList(R.color.green));
        }
    }

    @SuppressLint("SetTextI18n")
    private void showMealDetails() {
        List<Integer> calories = currentUser.getCaloriesToday().getCaloriesConsumed();
        List<String> foodConsumed = currentUser.getCaloriesToday().getFoodConsumed();
        List<String> restaurants = currentUser.getCaloriesToday().getFoodRestaurant();

        if (!calories.isEmpty() && calories.get(0) != null) {
            addMealToContainer((LinearLayout) breakfastContainer, restaurants.get(0), foodConsumed.get(0), calories.get(0));
        }
        else
        {
            addBlankMeal((LinearLayout) breakfastContainer);
        }
        if (calories.size() > 1 && calories.get(1) != null) {
            addMealToContainer((LinearLayout) lunchContainer, restaurants.get(1), foodConsumed.get(1), calories.get(1));
        }
        else {
            addBlankMeal((LinearLayout) lunchContainer);
        }
        if (calories.size() > 2 && calories.get(2) != null) {
            addMealToContainer((LinearLayout) dinnerContainer, restaurants.get(2), foodConsumed.get(2), calories.get(2));
        }
        else {
            addBlankMeal((LinearLayout) dinnerContainer);
        }

        miscMealsContainer.removeAllViews();

        // Handle MISC meals (index 3 and higher)
        for (int i = 3; i < calories.size(); i++) {
            if (calories.get(i) != null) {
                addMealToContainer((LinearLayout) miscMealsContainer, restaurants.get(i), foodConsumed.get(i), calories.get(i));
            }
        }
    }
    // Helper method to add a meal to the appropriate container
    @SuppressLint("SetTextI18n")
    private void addMealToContainer(LinearLayout container, String restaurant, String meal, Integer calories) {
        View mealView = getLayoutInflater().inflate(R.layout.misc_meal_item, container, false);

        TextView tvRestaurantName = mealView.findViewById(R.id.tvRestaurantName);
        TextView tvMealName = mealView.findViewById(R.id.tvMealName);
        TextView tvMealCalories = mealView.findViewById(R.id.tvMealCalories);

        String restaurantName = restaurant != null ? restaurant : "Unknown Restaurant";
        String mealName = meal != null ? meal : "Unnamed Meal";
        if (calories == 0)
        {
            restaurantName = "Not added yet";
            mealName = "";
        }

        tvRestaurantName.setText(restaurantName);
        tvMealName.setText(mealName);
        tvMealCalories.setText("Calories: " + calories);

        container.addView(mealView);
    }
    private void addBlankMeal(LinearLayout container) {
        View mealView = getLayoutInflater().inflate(R.layout.misc_meal_item, container, false);

        TextView tvRestaurantName = mealView.findViewById(R.id.tvRestaurantName);

        String restaurantName = "Not added yet";

        tvRestaurantName.setText(restaurantName);

        container.addView(mealView);
    }


    private void addCalories() {
        showAddCalorieDialog(MealType.MISC);
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

            UserController.updateCalories(restaurantId, foodId, calories, selectedMealType);

            showMealDetails();
            setupCaloriesProgress(currentUser.getCaloriesToday().getTotalCalories(),
                    currentUser.getTargetCalories());
            dialog.dismiss();

        });
    }

}
