package com.nutriroute.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nutriroute.R;
import com.nutriroute.fragments.AdminRestaurantClaimsFragment;
import com.nutriroute.fragments.AdminMenuChangeFragment;
import com.nutriroute.fragments.AdminSettingsFragment;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.Admin;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;


public class AdminActivity extends AppCompatActivity {
    Admin currentUser = (Admin) AuthStore.getCurUser();

    private static final IDataStore<String> dataStore = ServiceLocator.getDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new AdminRestaurantClaimsFragment();

            dataStore._loadObjFromDB(Restaurant.class, null);
            dataStore._loadObjFromDB(Request.class, null);

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


