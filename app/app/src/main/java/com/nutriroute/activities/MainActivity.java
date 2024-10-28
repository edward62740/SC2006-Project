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
import java.time.LocalTime;
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
       // __TEST__generateRandomDB();
        //_addNewRestaurantToDB();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



      //  System.out.print(dataStore.getUser("User1"));



    }

    protected void _addNewRestaurantToDB(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("TestItem", "TestDescription", 1.23, "TestCategory", "", 1520));
        Menu menu = new Menu(menuItemList);
        Restaurant restaurant = new Restaurant(menu, "TestRestaurant", "637553", "65837931", "Test@gmail.com", "www.Test.com", "TestDescription", "", "TestRestaurant", "08:00", "20:30");
        dataStore.setRestaurant(restaurant);
    }

    //dummy data for testing and demonstration
    protected void __TEST__generateRandomDB()
    {String[] validPostalCode = {"637553", "637034", "639798", "637335", "636922", "639798", "639798", "638075", "636922", "321789"};

        // bunch of random images
        String[] validImageUrl = {"https://github.com/hpatches/hpatches-dataset/blob/master/img/detections.png?raw=true",
        "https://www.researchgate.net/publication/366230142/figure/fig2/AS:11431281115030222@1674751733108/SuperPoint-network-model.png",
        "https://ar5iv.labs.arxiv.org/html/1712.07629/assets/x1.png",
        "https://www.researchgate.net/profile/Ciaran-Eising/publication/358598875/figure/fig2/AS:1126639740170247@1645623254951/Self-supervised-fisheye-Keypoint-Detection-and-Description-training-framework-adapted_Q320.jpg",
        "https://d3i71xaburhd42.cloudfront.net/a62bdda9ae6f86fc06d7edf5d3b429eda3a6640e/6-Figure7-1.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNqNI0JDuleRuP1DZnxVh6ERXvmEc72faS-s7CAHcxi2Otko1CUMqg1cEW1zLIJ52mk6E&usqp=CAU",
        "https://production-media.paperswithcode.com/methods/Screen_Shot_2021-01-26_at_9.43.31_PM_uI4jjMq.png",
        "https://xuranpan.plus/publication/dat/featured_hu2a5919fcf9b44331e6d70800bb6ac312_1031328_720x2500_fit_q75_h2_lanczos_3.webp"};

        /*
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
        */


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
                menuItem.setImage(validImageUrl[(int) (Math.random() * validImageUrl.length)]);
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
            Restaurant restaurant = new Restaurant(menu, name, address, phone, " ", " ", description, " ", someId, "08:00", "20:30");
            restaurant.setMenu(menu);
            restaurant.setImage(validImageUrl[(int) (Math.random() * validImageUrl.length)]);
            // Set the restaurant in the data store
            dataStore.setRestaurant(restaurant);

            // Output to show restaurant creation
            System.out.println("Created restaurant: " + name + " with ID: " + someId);


        }




    }



}
