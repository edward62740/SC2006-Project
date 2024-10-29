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
        //__TEST__generateDemoRestaurants();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



      //  System.out.print(dataStore.getUser("User1"));



    }

    protected void _addNewRestaurantToDB(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("TestItem", "TestDescription", 1.23, "TestCategory", "", 1520));
        Menu menu = new Menu(menuItemList, "TestRestaurant");
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
            Menu menu = new Menu(new ArrayList<>(), "");

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

    protected void __TEST__generateDemoRestaurants(){
        Restaurant mcdonalds, subway, starbucks, pastaExpress, hotHideout;
        Menu mcdonaldsMenu, subwayMenu, starbucksMenu, pastaExpressMenu, hotHideoutMenu;
        List<MenuItem> mcdonaldsMenuItems, subwayMenuItems, starbucksMenuItems, pastaExpressMenuItems, hotHideoutMenuItems;

        mcdonaldsMenuItems = new ArrayList<>();
        mcdonaldsMenuItems.add(new MenuItem("Big Mac", "Big Mac", 7.60, "Burger", "https://www.mcdonalds.com.sg/sites/default/files/2023-02/1200x1200_MOP_BBPilot_BigMac.png", 558));
        mcdonaldsMenuItems.add(new MenuItem("McSpicy", "McSpicy", 7.30, "Burger", "https://www.mcdonalds.com.sg/sites/default/files/2023-02/1200x1200_MOP_BBPilot_McSpicy.png", 541));
        mcdonaldsMenuItems.add(new MenuItem("Filet-O-Fish", "Filet-O-Fish", 6.25, "Burger", "https://www.mcdonalds.com.sg/sites/default/files/2023-02/1200x1200_MOP_BBPilot_FOF.png", 332));
        mcdonaldsMenuItems.add(new MenuItem("McChicken", "McChicken", 5.30, "Burger", "https://www.mcdonalds.com.sg/sites/default/files/2023-02/1200x1200_MOP_BBPilot_McChicken.png", 391));
        mcdonaldsMenu = new Menu(mcdonaldsMenuItems, "McDonald's");

        subwayMenuItems = new ArrayList<>();
        subwayMenuItems.add(new MenuItem("Avo Caesar Chicken", "Avo Caesar Chicken", 9.70, "6-inch", "https://www.subdelivery.com.sg/orders/axmenu/images/category/113/20241017060104-sub-chick-avo.jpg", 558));
        subwayMenuItems.add(new MenuItem("Californian Avocado Club", "Californian Avocado Club", 10.80, "6-inch", "https://www.subdelivery.com.sg/orders/axmenu/images/category/105/20240815074544-20231114052713-californian-avocado-club%20copy.jpg", 541));
        subwayMenuItems.add(new MenuItem("Italian Supreme", "Italian Supreme", 10.20, "6-inch", "https://www.subdelivery.com.sg/orders/axmenu/images/category/102/20231114052728-italian-supreme.jpg", 332));
        subwayMenuItems.add(new MenuItem("Chunky Beef Steak", "Chunky Beef Steak", 9.80, "6-inch", "https://www.subdelivery.com.sg/orders/axmenu/images/category/86/20200406103945-Chunky%20Beef%20Steak%20&%20Cheese.png", 391));
        subwayMenu = new Menu(subwayMenuItems, "Subway");

        starbucksMenuItems = new ArrayList<>();
        starbucksMenuItems.add(new MenuItem("Caffe Latte", "Caffe Latte", 7.30, "Espresso", "https://food-cms.grab.com/compressed_webp/items/SGITE20230821101652015638/detail/15d84b7b_540lattec0.webp", 150));
        starbucksMenuItems.add(new MenuItem("Flat White", "Flat White", 8.10, "Espresso", "https://food-cms.grab.com/compressed_webp/items/SGITE20230821101655011726/detail/19f324d4_latwhitec0.webp", 145));
        starbucksMenuItems.add(new MenuItem("Vanilla Latte", "Vanilla Latte", 8.00, "Espresso", "https://food-cms.grab.com/compressed_webp/items/SGITE20230821101656013940/detail/23192703_llalattec5.webp", 180));
        starbucksMenuItems.add(new MenuItem("Caffe Mocha", "Caffe Mocha", 8.00, "Espresso", "https://food-cms.grab.com/compressed_webp/items/SGITE20230821101700013951/detail/0d1fcd83_080mochac5.webp", 231));
        starbucksMenu = new Menu(starbucksMenuItems, "Starbucks");

        pastaExpressMenuItems = new ArrayList<>();
        pastaExpressMenuItems.add(new MenuItem("Carbonara", "Carbonara", 9.00, "Pasta", "https://images.deliveryhero.io/image/fd-sg/Products/5359497.jpg?width=%s", 364));
        pastaExpressMenuItems.add(new MenuItem("Smoked Duck Aglio Oglio", "Smoked Duck Aglio Oglio", 9.00, "Pasta", "https://images.deliveryhero.io/image/fd-sg/Products/5376529.jpg?width=%s", 578));
        pastaExpressMenuItems.add(new MenuItem("Bolognese", "Bolognese", 9.00, "Pasta", "https://images.deliveryhero.io/image/fd-sg/Products/5376527.jpg?width=%s", 830));
        pastaExpressMenuItems.add(new MenuItem("Meat Lovers", "Meat Lovers", 10.20, "Pasta", "https://images.deliveryhero.io/image/fd-sg/Products/46063921.jpg?width=%s", 250));
        pastaExpressMenu = new Menu(pastaExpressMenuItems, "Pasta Express");

        hotHideoutMenuItems = new ArrayList<>();
        hotHideoutMenuItems.add(new MenuItem("Mala Collagen Soup", "Mala Collagen Soup", 3.00, "Soup", "https://order.ahothideout.com/assets/images/atlas-core-active-storage/c7p00wvnmt4whyeo809hhdtrpt9o", 553));
        hotHideoutMenuItems.add(new MenuItem("Mala Stir Fry", "Mala Stir Fry", 2.00, "Soup", "https://order.ahothideout.com/assets/images/atlas-core-active-storage/9axgu9zqsyd6uti693bbmrm2jxry", 541));
        hotHideoutMenuItems.add(new MenuItem("Fried Lotus", "Fried Lotus", 5.80, "Ingredients", "https://order.ahothideout.com/assets/images/atlas-core-active-storage/osrhqfcakh6vrnovxwgxkvlavvn4", 158));
        hotHideoutMenuItems.add(new MenuItem("Pork Belly Slice", "Pork belly Slice", 3.60, "Ingredients", "https://order.ahothideout.com/assets/images/atlas-core-active-storage/rzd1d1mmj181tocw6fodjxtggw18", 518));
        hotHideoutMenu = new Menu(hotHideoutMenuItems, "Hot Hideout");

        mcdonalds = new Restaurant(mcdonaldsMenu, "McDonald's", "637331", "67773777", "", "https://www.mcdonalds.com.sg", "McDonald's @ NTU", "https://images.deliveryhero.io/image/fd-sg/sg-logos/cl8ox-logo.jpg", "McDonald's", "07:00", "22:00");
        dataStore.setRestaurant(mcdonalds);
        subway = new Restaurant(subwayMenu, "Subway", "637331", "64625238", "", "https://www.subway.com/en-SG/", "Subway @ NTU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpQeQDsNkoCdhhaAUJ4qXLiygT3dwaY2ZZYQ&s", "Subway", "07:00", "22:00");
        dataStore.setRestaurant(subway);
        starbucks = new Restaurant(starbucksMenu, "Starbucks", "637331", "69101245", "", "https://www.starbucks.com.sg/", "Starbucks @ NTU", "https://www.paragon.com.sg/media/media/images/stores/Starbucks/starbucks_logo.jpg", "Starbucks", "07:00", "22:00");
        dataStore.setRestaurant(starbucks);
        pastaExpress = new Restaurant(pastaExpressMenu, "Pasta Express", "639798", "90258148", "", "", "Pasta Express @ NTU", "https://images.deliveryhero.io/image/fd-sg/Products/5376536.jpg?width=%s", "Pasta Express", "10:00", "19:30");
        dataStore.setRestaurant(pastaExpress);
        hotHideout = new Restaurant(hotHideoutMenu, "Hot Hideout", "636957", "", "", "https://www.ahothideout.com/", "A Hot Hideout @ NTU", "https://lh3.googleusercontent.com/p/AF1QipPfZmBB0imyAvxZRRnlh0QyLRzi4ZS9T8KXCIf8=s1360-w1360-h1020", "Hot Hideout", "11:00", "21:30");
        dataStore.setRestaurant(hotHideout);

        List<String> addList = new ArrayList<>();
        addList.add(mcdonalds.getId());
        addList.add(subway.getId());
        addList.add(starbucks.getId());
        addList.add(pastaExpress.getId());
        addList.add(hotHideout.getId());

        if (dataStore.getUser("NTU") == null) {
            Vendor ntu = new Vendor("NTU", "NTU@gmail.com", "NTU", "NTU");
            ntu.setNewAccount(false);
            ntu.addRestaurant(addList.get(0));
            ntu.addRestaurant(addList.get(1));
            ntu.addRestaurant(addList.get(2));
            ntu.addRestaurant(addList.get(3));
            ntu.addRestaurant(addList.get(4));

            dataStore.setUser(ntu, ntu.getId());
        }
        else {
            Vendor ntu = (Vendor) dataStore.getUser("NTU");
            ntu.setRestaurants(addList);
        }

    }



}
