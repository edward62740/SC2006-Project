package com.nutriroute.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nutriroute.R;
import com.nutriroute.fragments.VendorClaimStoreFragment;
import com.nutriroute.fragments.VendorMoreFragment;
import com.nutriroute.fragments.VendorStatusFragment;
import com.nutriroute.fragments.VendorStoresFragment;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.Vendor;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

public class VendorActivity extends AppCompatActivity {
    Vendor currentUser = (Vendor) AuthStore.getCurUser();
    private static final IDataStore<String> dataStore = ServiceLocator.getDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = new VendorStoresFragment();

            dataStore._loadObjFromDB(Restaurant.class, null);
            dataStore._loadObjFromDB(Request.class, null);

            if(item.getItemId() == R.id.nav_vendor_stores) {
                selectedFragment = new VendorStoresFragment();
            }
            else if (item.getItemId() == R.id.nav_vendor_status) {
                selectedFragment = new VendorStatusFragment();
            }
            else if (item.getItemId() == R.id.nav_vendor_more) {
                selectedFragment = new VendorMoreFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        // Set default fragment
        if (currentUser.isNewAccount())
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VendorClaimStoreFragment()).commit();
        else
            bottomNavigationView.setSelectedItemId(R.id.nav_vendor_stores);

    }
}
