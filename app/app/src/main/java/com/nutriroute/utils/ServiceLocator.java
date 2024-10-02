package com.nutriroute.utils;

import com.nutriroute.stores.FirebaseDataStore;

/**
 * Service locator for the app.
 * Negates reference passing requirements
 */
public class ServiceLocator {
    static FirebaseDataStore<String> dataStore = new FirebaseDataStore<>(null); // this means the KEYS are all strings

    private ServiceLocator() {
    }

    public static FirebaseDataStore<String> getDB() {
        return dataStore;
    }
}
