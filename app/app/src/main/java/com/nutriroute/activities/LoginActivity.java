package com.nutriroute.activities;

import static com.nutriroute.enums.UserType.USER;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
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
    private View skeletonLoader;
    private TextView nutrirouteTextView;

    IDataStore<String> dataStore = ServiceLocator.getDB();

    IGenericUserManagementService userManagementService = new GenericUserManagementService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skeletonLoader = findViewById(R.id.skeleton_loader);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        nutrirouteTextView = findViewById(R.id.nutriroute_title);
        skeletonLoader.setVisibility(View.GONE);
        startColorAnimation();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }

    private void startColorAnimation() {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                getResources().getColor(R.color.light_green), // Start color (black)
                getResources().getColor(R.color.dark_green)); // End color (red)

        colorAnimation.setDuration(1500); // Duration of the animation
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                nutrirouteTextView.setTextColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    private void handleLogin() {
        skeletonLoader.setVisibility(View.GONE);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Toast.makeText(this, "Backend is loading ...", Toast.LENGTH_SHORT).show();

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
                    skeletonLoader.setVisibility(View.VISIBLE);
                    User user = (User) dataStore.getUser(username);
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                else if (userType == UserType.ADMIN) {
                   // Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                   // startActivity(intent);
                }
                else if (userType == UserType.VENDOR) {
                   // Intent intent = new Intent(LoginActivity.this, VendorActivity.class);
                   // startActivity(intent);
                }


            } else {
                System.out.println(dataStore.getUser("User1"));
                Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
