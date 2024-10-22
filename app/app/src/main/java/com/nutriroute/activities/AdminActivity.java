package com.nutriroute.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nutriroute.R;
import com.nutriroute.fragments.AdminRestaurantClaimsFragment;
import com.nutriroute.fragments.AdminMenuChangeFragment;
import com.nutriroute.fragments.AdminSettingsFragment;
import com.nutriroute.models.Admin;
import com.nutriroute.stores.AuthStore;


public class AdminActivity extends AppCompatActivity {
    Admin currentUser = (Admin) AuthStore.getCurUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new AdminRestaurantClaimsFragment();

            if(item.getItemId() == R.id.nav_admin_claims) {
                selectedFragment = new AdminRestaurantClaimsFragment();

            }
            else if (item.getItemId() == R.id.nav_admin_menu) {
                selectedFragment = new AdminMenuChangeFragment();
            }
            else if (item.getItemId() == R.id.nav_admin_settings) {
                selectedFragment = new AdminSettingsFragment();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        // Set default fragment
        bottomNavigationView.setSelectedItemId(R.id.nav_admin_claims);

    }
}


