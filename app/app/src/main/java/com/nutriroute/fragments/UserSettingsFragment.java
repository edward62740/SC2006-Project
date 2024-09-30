package com.nutriroute.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.google.android.material.snackbar.Snackbar;
import com.nutriroute.R;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

public class UserSettingsFragment extends Fragment {

    private User currentUser;

    private SeekBar heightSeekBar;
    private SeekBar weightSeekBar;
    private SeekBar targetCaloriesSeekBar;
    private SeekBar targetWeightSeekBar;
    private ToggleButton prescribedToggleButton;

    private TextView heightValue;
    private TextView weightValue;
    private TextView targetCaloriesValue;
    private TextView targetWeightValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_settings, container, false);

        currentUser = (User) AuthStore.getCurUser();
        IDataStore<String> dataStore = ServiceLocator.getDB();

        // Initialize views
        heightSeekBar = view.findViewById(R.id.heightSeekBar);
        weightSeekBar = view.findViewById(R.id.weightSeekBar);
        targetCaloriesSeekBar = view.findViewById(R.id.targetCaloriesSeekBar);
        targetWeightSeekBar = view.findViewById(R.id.targetWeightSeekBar);
        prescribedToggleButton = view.findViewById(R.id.prescribedToggleButton);

        heightValue = view.findViewById(R.id.heightValue);
        weightValue = view.findViewById(R.id.weightValue);
        targetCaloriesValue = view.findViewById(R.id.targetCaloriesValue);
        targetWeightValue = view.findViewById(R.id.targetWeightValue);

        Button saveButton = view.findViewById(R.id.saveButton);


        // Set initial values to SeekBars from current User attributes
        heightSeekBar.setProgress(currentUser.getHeight());
        weightSeekBar.setProgress(currentUser.getWeight());
        targetCaloriesSeekBar.setProgress(currentUser.getTargetCalories());
        targetWeightSeekBar.setProgress(currentUser.getTargetWeight());
        prescribedToggleButton.setChecked(currentUser.isPrescribed());

        // Display initial values in TextViews
        heightValue.setText("Height: " + String.valueOf(currentUser.getHeight()));
        weightValue.setText("Weight: " + String.valueOf(currentUser.getWeight()));
        targetCaloriesValue.setText("Target calories: " + String.valueOf(currentUser.getTargetCalories()));
        targetWeightValue.setText("Target weight: " + String.valueOf(currentUser.getTargetWeight()));

        // Update displayed values when sliders are changed
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                heightValue.setText("Height: " + String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weightValue.setText("Weight: " + String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        targetCaloriesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                targetCaloriesValue.setText("Target calories: " + String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        targetWeightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                targetWeightValue.setText("Target weight: " + String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Save button action
        saveButton.setOnClickListener(v -> {
            currentUser.setHeight(heightSeekBar.getProgress());
            currentUser.setWeight(weightSeekBar.getProgress());
            currentUser.setTargetCalories(targetCaloriesSeekBar.getProgress());
            currentUser.setTargetWeight(targetWeightSeekBar.getProgress());

            currentUser.setPrescribed(prescribedToggleButton.isChecked());
            dataStore.setUser(currentUser, currentUser.getId());
            Snackbar.make(view, "Settings saved", Snackbar.LENGTH_SHORT).show();
            // Save user changes in AuthStore if necessary
        });

        return view;
    }
}
