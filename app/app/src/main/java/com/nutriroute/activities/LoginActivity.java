package com.nutriroute.activities;

import static com.nutriroute.enums.UserType.USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.nutriroute.R;
import com.nutriroute.controllers.AuthController;
import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IGenericUserManagementService;
import com.nutriroute.models.User;
import com.nutriroute.services.GenericUserManagementService;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    IDataStore<String> dataStore = ServiceLocator.getDB();

    IGenericUserManagementService userManagementService = new GenericUserManagementService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        System.out.println(dataStore.getUser("User1"));
        System.out.println(dataStore.getUser("Use4r2"));

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Call the controller to handle login
            // Assuming Controller is a singleton or a static instance
            UserType userType = AuthController.login(username, password);
            if (userType != null) {
                AuthStore.setCurUser(dataStore.getUser(username));
                // Navigate to the next activity
                if(userType == USER) {
                    User user = (User) dataStore.getUser(username);
                    System.out.println(user);
                    System.out.println(user.getCaloriesHistory());
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                }


            } else {
                Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
