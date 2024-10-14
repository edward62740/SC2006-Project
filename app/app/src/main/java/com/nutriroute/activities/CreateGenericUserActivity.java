package com.nutriroute.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nutriroute.R;
import com.nutriroute.enums.UserType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.GenericUser;
import com.nutriroute.utils.GenericUserFactory;
import com.nutriroute.utils.ServiceLocator;

public class CreateGenericUserActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText idEditText;
    private TextView createUserTitle;
    private Button createUserButton;
    private UserType selectedUserType;

    IDataStore<String> dataStore = ServiceLocator.getDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_genericuser);

        nameEditText = findViewById(R.id.nameEditText);
        idEditText = findViewById(R.id.idEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        createUserButton = findViewById(R.id.createUserButton);
        createUserTitle = findViewById(R.id.create_user_title);

        showUserTypeSelectionDialog();

        createUserButton.setOnClickListener(v -> handleCreateUser());

        // Add TextWatcher to nameEditText
        nameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                if(name.isEmpty()) {
                    createUserTitle.setText("Welcome, new " + selectedUserType.toString());
                }
                else createUserTitle.setText("Welcome, " + name);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

    }

    @SuppressLint("SetTextI18n")
    private void showUserTypeSelectionDialog() {

        String[] userTypes = new String[UserType.values().length];

        for (int i = 0; i < UserType.values().length; i++) {
            userTypes[i] = UserType.values()[i].toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select User Type")
                .setSingleChoiceItems(userTypes, -1, (dialog, which) -> {

                    switch (which) {
                        case 0:
                            selectedUserType = UserType.USER;
                            break;
                        case 1:
                            selectedUserType = UserType.ADMIN;
                            break;
                        case 2:
                            selectedUserType = UserType.VENDOR;
                            break;
                    }
                })
                .setPositiveButton("OK", (dialog, id) -> {
                    createUserTitle.setText("Welcome, new " + selectedUserType.toString());
                    dialog.dismiss();

                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    dialog.dismiss();
                    finish(); // Close the activity if the user cancels
                });

        builder.create().show();
    }

    private void handleCreateUser() {
        String name = nameEditText.getText().toString();
        String id = idEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || selectedUserType == null) {
            Toast.makeText(this, "Please fill in all fields and select a user type", Toast.LENGTH_SHORT).show();
            return;
        }

        GenericUser<String> newUser = GenericUserFactory.createUser(selectedUserType);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setId(id);

        dataStore.setUser(newUser, newUser.getId());

        Toast.makeText(this, "User created successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}