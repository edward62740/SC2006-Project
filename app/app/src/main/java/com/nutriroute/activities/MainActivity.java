package com.nutriroute.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.Admin;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.models.Vendor;
import com.nutriroute.utils.ServiceLocator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    IDataStore<String> dataStore = ServiceLocator.getDB();

    public void _n_async_test_cb() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
       // __TEST__generateRandomDB();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



      //  System.out.print(dataStore.getUser("User1"));



    }

    public CompletableFuture<Void> waitForPendingUpdate() {
        return CompletableFuture.runAsync(() -> {
            while (dataStore.isPendingUpdate() > 0) {
                try {
                    // Delay for 500 milliseconds (or your desired time)
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    break; // Exit if interrupted
                }
            }
            // After the wait, you can check the final state
            System.out.println("Final pending update count: " + dataStore.isPendingUpdate());
        });
    }

    protected void __TEST__generateRandomDB()
    {
        for (int i = 1; i <= 25; i++) {
            String userName = "User" + i;
            String email = i + "@gmail.com";
            String pw = i + "pw";
            String id = userName; // Generate a unique UUID for each user

            // Create a new user
            User user = new User(userName, email, pw, id);
            user.setTodayCalories(1200);
            List<CalorieDay> calorieDays = user.getCaloriesHistory();
            for (int j = 0; j < (int) (Math.random() * 31); j++) {
                // generate Date j days ago
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, -j);
                Date nDaysBefore = calendar.getTime();
                CalorieDay calorieDay = new CalorieDay(nDaysBefore);


                for (int k = 0; k < (int) (Math.random() * 5); k++) {
                    calorieDay.addCaloriesConsumed( (int) (Math.random() * 834));
                    calorieDay.addFoodConsumed("Menu" + (int) (Math.random() * 23));
                    calorieDay.addFoodRestaurant("Restaurant" + (int) (Math.random() * 23));
                }

                calorieDays.add(calorieDay);
            }
            // Set the user in the data store
            dataStore.setUser(user, user.getId());

            // Output to show user creation
            System.out.println("Created user: " + userName + " with ID: " + id);
        }
        for (int i = 1; i <= 16; i++) {
            String userName = "Vendor" + i;
            String email = i + "@gmail.com";
            String pw = i + "pw";
            String id = userName; // Generate a unique UUID for each user

            // Create a new user
            Vendor user = new Vendor(userName, email, pw, id);

            // Set the user in the data store
            dataStore.setUser(user, user.getId());


            // Output to show user creation
            System.out.println("Created vendor: " + userName + " with ID: " + id);
        }
        for (int i = 1; i <= 8; i++) {
            String userName = "Admin" + i;
            String email = i + "@gmail.com";
            String pw = i + "pw";
            String id = userName; // Generate a unique UUID for each user

            // Create a new user
            Admin user = new Admin(userName, email, pw, id);

            // Set the user in the data store
            dataStore.setUser(user, user.getId());

            // Output to show user creation
            System.out.println("Created admin: " + userName + " with ID: " + id);
        }
        for (int i = 1; i <= 23; i++) {
            String name = "Restaurant" + i;
            String address = i + " Main St";
            String phone = "123-456-78" + String.format("%02d", i); // Incrementing phone numbers
            String someId = java.util.UUID.randomUUID().toString(); // Unique identifier

            // Create a new restaurant
            Restaurant restaurant = new Restaurant(null, name, address, phone, " ", " ", " ", " ", someId);

            // Set the restaurant in the data store
            dataStore.setRestaurant(restaurant);

            // Output to show restaurant creation
            System.out.println("Created restaurant: " + name + " with ID: " + someId);
        }
    }



}