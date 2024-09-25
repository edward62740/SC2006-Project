package com.nutriroute.activities;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nutriroute.fragments.UserDashboardFragment;
import com.nutriroute.R;
import com.nutriroute.fragments.UserStoresFragment;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new UserDashboardFragment();

            if(item.getItemId() == R.id.nav_user_dashboard) {
                selectedFragment = new UserDashboardFragment();
            }
            else if (item.getItemId() == R.id.nav_user_stores) {
                selectedFragment = new UserStoresFragment();
            }

            else if (item.getItemId() == R.id.nav_user_diary) {

                Snackbar.make(findViewById(R.id.bottom_navigation), "not impl", Snackbar.LENGTH_LONG).show();

            }

            else if (item.getItemId() == R.id.nav_user_more) {
                Snackbar.make(findViewById(R.id.bottom_navigation), "not impl", Snackbar.LENGTH_LONG).show();
            }


            // Replace the current fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_user_dashboard);
        }
    }

}
