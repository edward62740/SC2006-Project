package com.nutriroute.activities;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nutriroute.R;
import com.nutriroute.controllers.AdminController;
import com.nutriroute.fragments.AdminDashboardFragment;
import com.nutriroute.fragments.AdminClaimsFragment;
import com.nutriroute.fragments.AdminMenuRequestsFragment;
import com.nutriroute.fragments.AdminSettingsFragment;

public class AdminActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        AdminController.run(); // update, state transition of user

        // view frag state transition
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new AdminDashboardFragment();

            if(item.getItemId() == R.id.nav_admin_dashboard) {
                selectedFragment = new AdminDashboardFragment();
            }
            else if (item.getItemId() == R.id.nav_admin_claims) {
                selectedFragment = new AdminClaimsFragment(); // Handles restaurant claims
            }
            else if (item.getItemId() == R.id.nav_admin_menu_requests) {
                selectedFragment = new AdminMenuRequestsFragment(); // Handles menu requests
            }
            else if (item.getItemId() == R.id.nav_admin_settings) {
                selectedFragment = new AdminSettingsFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();


            return true;
        });

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_admin_dashboard);
        }
    }


    public void showDecisionPopup(View view, String claimOrRequestDetails) {
        // Code to display a dialog or popup box
        AdminDecisionPopupFragment popupFragment = AdminDecisionPopupFragment.newInstance(claimOrRequestDetails);
        popupFragment.show(getSupportFragmentManager(), "AdminDecisionPopup");
    }
}
}


