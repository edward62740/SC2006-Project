package com.nutriroute.stores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.nutriroute.adapters.LocalDateAdapter;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.Menu;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.utils.GenericUserFactory;
import com.nutriroute.utils.RequestFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.Map;
import java.util.function.Function;

/**
 * This is the class which implements the IDataStore interface.
 * It performs R/W ops with Firebase RTDB, with read-through LRU cache.
 * The writes are done exclusively on the Firebase RTDB to ensure consistency.
 *
 * Provide the conv argument to the constructor, to specify how the key T is converted to a string.
 */
public class FirebaseDataStore<T extends Comparable<T>> implements IDataStore<T> {

    private final DatabaseReference databaseReference;
    private final Gson gson;
    private final Function<T, String> keyToString;
    private int isPendingUpdateCount = 0;

    // These are the write through caches
    LRUMap<String, GenericUser<T>> genericUserCache = new LRUMap<>(10, 100);
    LRUMap<String, Restaurant> restaurantCache = new LRUMap<>(10, 100);
    LRUMap<String, Menu> menuCache = new LRUMap<>(10, 100);
    LRUMap<String, Request<T>> requestCache = new LRUMap<>(10, 100);


    public FirebaseDataStore(Function<T, String> conv) {
        this.databaseReference = FirebaseDatabase.getInstance("https://sc2006-ecd77-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe());
        this.gson = gsonBuilder.create();
        this.keyToString = conv;
        Log.d("FirebaseDataStore", "Now, loading all objects from the database...");
        isPendingUpdateCount = 4;
        this._loadObjFromDB(GenericUser.class, this::_internal_cb);
        this._loadObjFromDB(Restaurant.class, this::_internal_cb);
        this._loadObjFromDB(Menu.class, this::_internal_cb);
        this._loadObjFromDB(Request.class, this::_internal_cb);
    }

    private void _internal_cb() {
        Log.d("FirebaseDataStore", "Loaded GenericUser with size: " + genericUserCache.size());
        Log.d("FirebaseDataStore", "Loaded Restaurant with size: " + restaurantCache.size());
        Log.d("FirebaseDataStore", "Loaded Menu with size: " + menuCache.size());
        Log.d("FirebaseDataStore", "Loaded Request with size: " + requestCache.size());
        isPendingUpdateCount--;
        System.out.println("isPendingUpdateCount: " + isPendingUpdateCount);
    }

    public int isPendingUpdate() {
        return isPendingUpdateCount;
    }

    public Gson getGson() {
        return gson;
    }

    public GenericUser<T> getUser(T id) {
        return genericUserCache.get(keyToString != null ? keyToString.apply(id) : id.toString());
    }


    public boolean setUser(GenericUser<T> user, T id) {
        this._updateObjectToDB(user, keyToString != null ? keyToString.apply(id) : id.toString());
        genericUserCache.put(id.toString(), user);
        return true;
    }

    public Restaurant getRestaurant(String id) {
        return restaurantCache.get(id);
    }

    public Menu getMenu(String id) {
        return menuCache.get(id);
    }

    public Request<T> getRequest(T id) {
        return requestCache.get(keyToString != null ? keyToString.apply(id) : id.toString());
    }

    public boolean setRestaurant(Restaurant restaurant) {
        this._updateObjectToDB(restaurant, restaurant.getId());
        restaurantCache.put(restaurant.getId(), restaurant);
        return true;
    }

    public boolean setMenu(Menu menu) {
        this._updateObjectToDB(menu, menu.getId());
        menuCache.put(menu.getId(), menu);
        return true;
    }

    public boolean setRequest(Request<T> request, T id) {
        this._updateObjectToDB(request, keyToString != null ? keyToString.apply(id) : id.toString());
        requestCache.put(request.getId().toString(), request);
        return true;
    }

    public CompletableFuture<List<Object>> _getObjFromDBAsyncLoad(Class<?> objClass) {
        CompletableFuture<List<Object>> future = new CompletableFuture<>();
        DatabaseReference dbRef = databaseReference.child(objClass.getSimpleName().toLowerCase());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        List<Object> objList = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            GenericTypeIndicator<Map<String, Object>> typeIndicator = new GenericTypeIndicator<Map<String, Object>>() {};
                            Map<String, Object> dataMap = snapshot.getValue(typeIndicator);
                            objList.add(dataMap);
                        }
                        future.complete(objList);
                    } else {
                        future.completeExceptionally(new Exception("No data found"));
                    }
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                future.completeExceptionally(new Exception("Error retrieving data: " + error.getMessage()));
            }
        });

        return future;
    }

    public void _loadObjFromDB(Class<?> objClass, Runnable callback) {
        CompletableFuture<List<Object>> future = this._getObjFromDBAsyncLoad(objClass);

        future.thenAccept(objList -> {
            List<Object> processedList = new ArrayList<>();
            for (Object obj : objList) {
                if (obj instanceof Map) {

                    String json = gson.toJson(obj);
                    JsonElement jsonElement = gson.fromJson(json, JsonElement.class);

                    if (GenericUser.class.isAssignableFrom(objClass)) {
                        GenericUser<?> user = GenericUserFactory.fromJson(jsonElement);
                        processedList.add(user);
                        genericUserCache.put(user.getId().toString(), (GenericUser<T>) user);
                    }
                    else if (Restaurant.class.isAssignableFrom(objClass)) {
                        Restaurant restaurant = gson.fromJson(json, Restaurant.class);
                        processedList.add(restaurant);
                        restaurantCache.put(restaurant.getId(), restaurant);
                    }
                    else if (Menu.class.isAssignableFrom(objClass)) {
                        Menu menu = gson.fromJson(json, Menu.class);
                        processedList.add(menu);
                        menuCache.put(menu.getId(), menu);
                    }
                    else if (Request.class.isAssignableFrom(objClass)) {
                        Request<?> req = RequestFactory.fromJson(jsonElement);
                        processedList.add(req);
                        requestCache.put(req.getId().toString(), (Request<T>) req);
                    }
                    else {
                        System.out.println("Unhandled object type: " + objClass.getSimpleName());
                    }
                }
            }
            if (callback != null) {
                callback.run();
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            if (callback != null) {
                callback.run();
            }
            return null;
        });

        System.out.println("Objects loaded successfully!");
    }

    private static boolean isCoreJavaClass(Class<?> clazz) {
        return clazz.getPackage().getName().startsWith("java.") ||
                clazz.getPackage().getName().startsWith("javax.");
    }
    public boolean _addObjectToDB(Object obj, String id) {
        try {
            String type;
            if(!isCoreJavaClass(Objects.requireNonNull(obj.getClass().getSuperclass())))
            {
                type = obj.getClass().getSuperclass().getSimpleName().toLowerCase();
            }
            else {
                type = obj.getClass().getSimpleName().toLowerCase();
            }

            String oid = id;
            String json = gson.toJson(obj);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});

            databaseReference.child(type).child(oid).setValue(jsonMap)
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseDataStore", "Object added successfully!"))
                    .addOnFailureListener(e -> Log.d("FirebaseDataStore", "Object add fail"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean _updateObjectToDB(Object obj, String id) {
        try {
            String oid = id;
            String type;
            if(!isCoreJavaClass(Objects.requireNonNull(obj.getClass().getSuperclass())))
            {
                type = obj.getClass().getSuperclass().getSimpleName().toLowerCase();
            }
            else {
                type = obj.getClass().getSimpleName().toLowerCase();
            }

            // Convert the user object to JSON and then to a Map
            String json = gson.toJson(obj);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});

            // Update the existing user data in the Firebase database
            databaseReference.child(type).child(oid).updateChildren(jsonMap)
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseDataStore", "Object update successfully!"))
                    .addOnFailureListener(e -> Log.d("FirebaseDataStore", "Object update fail"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean _deleteObjectFromDB(Object obj, String id) {
        try {
            String oid = id;
            String type;
            if(!isCoreJavaClass(Objects.requireNonNull(obj.getClass().getSuperclass())))
            {
                type = obj.getClass().getSuperclass().getSimpleName().toLowerCase();
            }
            else {
                type = obj.getClass().getSimpleName().toLowerCase();
            }

            // Remove the user entry from the Firebase database
            databaseReference.child(type).child(oid).removeValue()
                    .addOnSuccessListener(aVoid -> Log.d("FirebaseDataStore", "Object update successfully!"))
                    .addOnFailureListener(e -> Log.d("FirebaseDataStore", "Object update fail"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
