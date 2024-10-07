package com.nutriroute.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.Admin;
import com.nutriroute.models.CalorieDay;
import com.nutriroute.models.Menu;
import com.nutriroute.models.MenuItem;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.models.Vendor;
import com.nutriroute.utils.ServiceLocator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    IDataStore<String> dataStore = ServiceLocator.getDB();


    /**
     * This is the application entry point. Here, we start the login activity.
     * <p>
     * In general, the convention is that the ACTIVITY is the controller + view,
     * and the CONTROLLER is exclusively the high level logic.
     * The lower level logic is contained in the SERVICE classes.
     *
     * Note that the activity/fragments should NEVER instantiate the service classes.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
      //  __TEST__generateRandomDB();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



      //  System.out.print(dataStore.getUser("User1"));



    }

    //dummy data for testing and demonstration
    protected void __TEST__generateRandomDB()
    {String[] validPostalCode = {"637553", "637034", "639798", "637335", "636922", "639798", "639798", "638075", "636922", "321789"};

        String[] validImageUrl = {};


        for (int i = 1; i <= 25; i++) {
            String userName = "User" + i;
            String email = i + "@gmail.com";
            String pw = i + "pw";
            String id = userName; // Generate a unique UUID for each user

            // Create a new user
            User user = new User(userName, email, pw, id);

            List<CalorieDay> calorieDays = user.getCaloriesHistory();
            user.setCaloriesToday(new CalorieDay(LocalDate.now()));
            int n = Math.max(1, (int) (Math.random() * 31));
            for (int j = 0; j < n; j++) {
                CalorieDay calorieDay = new CalorieDay(LocalDate.now().minusDays(n-j));


                for (int k = 0; k < Math.max(1, (int) (Math.random() * 5)); k++) {
                    calorieDay.addCaloriesConsumed( (int) (Math.random() * 834), k);
                    calorieDay.addFoodConsumed("SomeMenuItem" + (int) (Math.random() * 23), k);
                    calorieDay.addFoodRestaurant("Restaurant" + (int) (Math.random() * 23), k);
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
            user.addRestaurant("Restaurant" + i);


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
        /*


        for (int x = 1; x <= 16; x++) { //restaurant
            String name = "Restaurant" + x;
            Menu menu = new Menu(new ArrayList<>());

            List<MenuItem> items = new ArrayList<>();
            for(int j = 1; j <= 16; j++) {
                String itemname = "MenuItem" + j + "ForRestaurant" + x;
                double price = Math.random() * 10; //round to 2dp
                price = Math.round(price * 100.0) / 100.0;
                MenuItem menuItem = new MenuItem(itemname, "description of item" + j,
                        price, "category" + (int) (Math.random() * 36), "", (int) (Math.random() * 2200));
                items.add(menuItem);
            }
            String menuname = "MenuForRestaurant" + x;
            String id = "Restaurant" + x; // let id be the same as the restaurant id for simplicity
            menu.setItems(items);
            menu.setId(id);
            System.out.println("Created menu: " + menuname + " with ID: " + id);

            String address = validPostalCode[(int) (Math.random() * validPostalCode.length)];
            String phone = "62382043" + String.format("%02d", x); // Incrementing phone numbers
            String someId = "Restaurant" + x;
            String description = "Description of restaurant" + x;

            // Create a new restaurant
            Restaurant restaurant = new Restaurant(null, name, address, phone, " ", " ", description, " ", someId);
            restaurant.setMenu(menu);

            // Set the restaurant in the data store
            dataStore.setRestaurant(restaurant);

            // Output to show restaurant creation
            System.out.println("Created restaurant: " + name + " with ID: " + someId);


        }
*/



    }



}
