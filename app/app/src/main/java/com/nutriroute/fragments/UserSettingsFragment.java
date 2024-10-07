package com.nutriroute.fragments;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.nutriroute.utils.Consts.NOTIFICATIONS_KEY;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.google.android.material.snackbar.Snackbar;
import com.nutriroute.R;
import com.nutriroute.controllers.UserController;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.Objects;

public class UserSettingsFragment extends Fragment {

    private User currentUser;

    private SeekBar heightSeekBar;
    private SeekBar weightSeekBar;
    private SeekBar targetCaloriesSeekBar;
    private SeekBar targetWeightSeekBar;
    private Switch prescribedSwitch, notificationSwitch;
    private Button saveButton, logoutButton;

    private TextView heightValue;
    private TextView weightValue;
    private TextView targetCaloriesValue;
    private TextView targetWeightValue;
    private TextView settingsTitle;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_settings, container, false);

        currentUser = (User) AuthStore.getCurUser();
        IDataStore<String> dataStore = ServiceLocator.getDB();

        heightSeekBar = view.findViewById(R.id.heightSeekBar);
        weightSeekBar = view.findViewById(R.id.weightSeekBar);
        targetCaloriesSeekBar = view.findViewById(R.id.targetCaloriesSeekBar);
        targetWeightSeekBar = view.findViewById(R.id.targetWeightSeekBar);
        prescribedSwitch = view.findViewById(R.id.prescribedSwitch);
        notificationSwitch = view.findViewById(R.id.notificationSwitch);

        heightValue = view.findViewById(R.id.heightValue);
        weightValue = view.findViewById(R.id.weightValue);
        targetCaloriesValue = view.findViewById(R.id.targetCaloriesValue);
        targetWeightValue = view.findViewById(R.id.targetWeightValue);

        saveButton = view.findViewById(R.id.saveButton);
        logoutButton = view.findViewById(R.id.logoutButton);

        settingsTitle = view.findViewById(R.id.settingsTitle);


        heightSeekBar.setProgress(currentUser.getHeight());
        weightSeekBar.setProgress(currentUser.getWeight());
        targetCaloriesSeekBar.setProgress(currentUser.getTargetCalories());
        targetWeightSeekBar.setProgress(currentUser.getTargetWeight());
        prescribedSwitch.setChecked(currentUser.isPrescribed());

        heightValue.setText("Height: " + currentUser.getHeight());
        weightValue.setText("Weight: " + currentUser.getWeight());
        targetCaloriesValue.setText("Target calories: " + currentUser.getTargetCalories());
        targetWeightValue.setText("Target weight: " + currentUser.getTargetWeight());



        if(currentUser.isNewAccount()) {
            settingsTitle.setText("Welcome, please fill in your information");
        } else {
            settingsTitle.setText("User Information");
        }

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Config", MODE_PRIVATE);
        notificationSwitch.setChecked(sharedPreferences.getBoolean(NOTIFICATIONS_KEY, false));


        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(NOTIFICATIONS_KEY, isChecked);

            if (isChecked) {
                // Create a push notification
                createPushNotification("Notifications Enabled", "You will now receive notifications.");
            } else {
                // Optionally, handle the case when notifications are turned off
                createPushNotification("Notifications Disabled", "You will no longer receive notifications.");
            }

            editor.apply();
        });



        // Update displayed values when sliders are changed
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
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
            @SuppressLint("SetTextI18n")
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
                targetCaloriesValue.setText("Target calories: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        targetWeightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                targetWeightValue.setText("Target weight: " + progress);
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

            currentUser.setPrescribed(prescribedSwitch.isChecked());
            dataStore.setUser(currentUser, currentUser.getId());
            currentUser.setNewAccount(false);
            Snackbar.make(view, "Settings saved", Snackbar.LENGTH_SHORT).show();
        });

        // Logout button action
        logoutButton.setOnClickListener(v -> {
            UserController.logoutAndExit();
            Snackbar.make(view, "Logging out...", Snackbar.LENGTH_SHORT).show();

            // this finishes the "user" activity from this frag
            requireActivity().finish();

        });

        return view;
    }

    // Method to create a push notification
    private void createPushNotification(String title, String message) {
        NotificationManager notificationManager =
                (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        // Create a notification channel for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "Channel ID", // Channel ID
                    "Channel Name", // Channel Name
                    NotificationManager.IMPORTANCE_DEFAULT // Importance
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "Channel ID")
                .setSmallIcon(R.drawable.ic_launcher_background) // put the app icon here
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

}
