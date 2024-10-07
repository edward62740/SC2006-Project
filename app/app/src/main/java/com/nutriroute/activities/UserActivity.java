package com.nutriroute.activities;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nutriroute.controllers.UserController;
import com.nutriroute.fragments.UserDashboardFragment;
import com.nutriroute.R;
import com.nutriroute.fragments.UserSettingsFragment;
import com.nutriroute.fragments.UserStoresFragment;
import com.nutriroute.fragments.UserDiaryFragment;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;

/**
 * This is the entry point for Users.
 * The assumed pre-condition is that the AuthStore will return a valid user that is logged in.
 * The post condition is that one of the fragments will be displayed.
 */
public class UserActivity extends AppCompatActivity {

    User currentUser = (User) AuthStore.getCurUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        /*
        Here, we call the underlying controller to handle the logic.
        Specifically this call handles the state transitions for all model objects.
        A state transition is defined as:
        - Updating current day
        - Pushing the User's "current day" to history if it is not today
        - Instantiating a new day if it is a new day
        - Dealing with NULL objects due to the behavior of retrieving empty objects from the DB
         */
        UserController.updateUserState();

        // Transition higher-level logic flow to the fragment
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new UserDashboardFragment();

            if(item.getItemId() == R.id.nav_user_dashboard) {
                selectedFragment = new UserDashboardFragment();
            }
            else if (item.getItemId() == R.id.nav_user_stores) {
                selectedFragment = new UserStoresFragment();
            }
            else if (item.getItemId() == R.id.nav_user_diary) {
                selectedFragment = new UserDiaryFragment();
            }
            else if (item.getItemId() == R.id.nav_user_settings) {
                selectedFragment = new UserSettingsFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        // Set default fragment
        if(currentUser.isNewAccount()) {
            bottomNavigationView.setSelectedItemId(R.id.nav_user_settings);
        } else if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_user_dashboard);
        }
    }

}
