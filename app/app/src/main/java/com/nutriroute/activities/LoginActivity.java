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

import com.nutriroute.R;
import com.nutriroute.controllers.AuthController;
import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.GenericUserFactory;
import com.nutriroute.utils.ServiceLocator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import android.util.Log;
import com.google.android.gms.common.SignInButton;



public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton, createAccountButton;
    private SignInButton googleLoginButton;
    private View skeletonLoader;
    private TextView nutrirouteTextView;

    // Authentication
        private static final int RC_SIGN_IN = 123;
        private GoogleSignInClient googleSignInClient;
        private FirebaseAuth firebaseAuth;

    IDataStore<String> dataStore = ServiceLocator.getDB();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();

        skeletonLoader = findViewById(R.id.skeleton_loader);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        // Google Login Button
        googleLoginButton = findViewById(R.id.googleLoginButton);


        createAccountButton = findViewById(R.id.createAccountButton);
        nutrirouteTextView = findViewById(R.id.nutriroute_title);
        skeletonLoader.setVisibility(View.GONE);
        startColorAnimation();

        loginButton.setOnClickListener(v -> handleLogin());

        // google login button
        googleLoginButton.setOnClickListener(v -> googleSignIn());

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CreateGenericUserActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        skeletonLoader.setVisibility(View.GONE);
    }

    private void startColorAnimation() {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                getResources().getColor(R.color.light_green), // Start color (black)
                getResources().getColor(R.color.white)); // End color (red)

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
            loginFlow(userType,username);

        }
    }


    private void loginFlow(UserType userType , String username)
    {
        if (userType != null) {
            AuthStore.setCurUser(dataStore.getUser(username));
            // Navigate to the next activity
            if(userType == USER) {
                skeletonLoader.setVisibility(View.VISIBLE);
                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
            else if (userType == UserType.ADMIN) {
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(intent);
            }
            else if (userType == UserType.VENDOR) {
                Intent intent = new Intent(LoginActivity.this, VendorActivity.class);
                startActivity(intent);
            }

        } else {

            Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


    private void googleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...)
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign-In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        // Step 1: Declare the firebaseEmail variable outside the block

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    String firebaseEmail = null;
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        // Handle UI changes accordingly

                        // Step 1 : Check if it new user
                        if (user != null) {
                            firebaseEmail = user.getEmail();  // This is your username (email)
                        }

                        // Remove @gmail.com from the text
                        String googleID = firebaseEmail.replace("@gmail.com", "");
                        UserType userType = AuthController.googleLogin(googleID);

                        if (userType != null){
                            loginFlow(userType , googleID);
                        }
                        else
                        {
//                            // Step 1.1: create a new user

                            assert user != null;
                            String name = user.getDisplayName();
                            String password = "null";

                            GenericUser<String> newUser = GenericUserFactory.createUser(USER);
                            newUser.setName(name);
                            newUser.setEmail(firebaseEmail);
                            newUser.setPassword(password);
                            newUser.setId(googleID);
//
                            dataStore.setUser(newUser, newUser.getId());

                            AuthStore.setCurUser(dataStore.getUser(googleID));

                            skeletonLoader.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                            startActivity(intent);

                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);



                        }

                        // If new user Add data into firebase


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                    }
                });
    }


}
