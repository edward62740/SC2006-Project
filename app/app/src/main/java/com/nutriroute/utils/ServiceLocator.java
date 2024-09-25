package com.nutriroute.utils;

import com.nutriroute.stores.FirebaseDataStore;

public class ServiceLocator {
    static FirebaseDataStore<String> dataStore = new FirebaseDataStore<>(null); // this means the KEYS are all strings
    // todo add google maps api

    private ServiceLocator() {
    }

    public static FirebaseDataStore<String> getDB() {
        return dataStore;
    }
}
