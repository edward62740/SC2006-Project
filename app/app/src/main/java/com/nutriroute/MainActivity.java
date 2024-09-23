package com.nutriroute;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.activities.LoginActivity;
import com.nutriroute.stores.ServiceLocator;

public class MainActivity extends AppCompatActivity {

    IDataStore<String> dataStore = ServiceLocator.getDB();

    public void _n_async_test_cb() {
        System.out.println("Hello World3");
        System.out.println(dataStore.getUser("a724bc3f-4b1d-4b3b-8b3b-4b3b4b3b4b3b").getEmail());
        User k = (User) dataStore.getUser("a724bc3f-4b1d-4b3b-8b3b-4b3b4b3b4b3b");
        k.setWeight(200);



        /* Test normal class */
        Restaurant r = new Restaurant(null, "McDonalds", "1234 Main St", "123-456-7890", " ", " ", " ", " ", "3s3");
        dataStore._addObjectToDB(r, "3s3");

        Restaurant r2 = dataStore.getRestaurant("3s3");
        System.out.println(r2);
        r2.setPhone("123-456-7890###############");
        dataStore.setRestaurant(r2);
        dataStore._deleteObjectFromDB(r2, "3s3");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        /* Test type parameterized class, multiple */
        User user = new User("John Doe", "ok", "hello", "a724bc3f-4b1d-4b3b-8b3b-4b3b4b3b4b3b");
        User user2 = new User("John Doe", "ok", "hello", "a724bc3f-4b1d-4b3b-8b3b-4b3bcb3b4b3b");

        dataStore._addObjectToDB(user, user.getId());   // add user to db
        dataStore._addObjectToDB(user2, user2.getId());
        //setContentView(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        dataStore._loadObjFromDB(GenericUser.class, this::_n_async_test_cb); // load user from db
        user.setWeight(200); // modify locally
        dataStore.setUser(user, user.getId()); // push the LRU to db
        System.out.println(user.getWeight()); // verify local
        dataStore._deleteObjectFromDB(user, "gibberish"); // deletion boundary case
        dataStore._deleteObjectFromDB(user, user.getId()); // deletion valid case
        System.out.print("now attempting local get");
        User user3 = new User("gsdfggdf ", "ok", "hello", "4b1d-4b3b-8b3b-4b3b4b3b4b3b");
        dataStore.setUser(user3, user3.getId()); // attempt create by setting


        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}