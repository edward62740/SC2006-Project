package com.nutriroute.stores;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.nutriroute.enums.UserType;
import com.nutriroute.models.Admin;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.User;
import com.nutriroute.models.Vendor;

public class GenericUserFactory {

    private static final Gson gson = new Gson();

    public static GenericUser<String> createUser(UserType userType) {
        switch (userType) {
            case ADMIN:
                return new Admin("", "", "", ""); // Replace with your actual AdminUser class
            case USER:
                return new User("", "", "", ""); // Replace with your actual CustomerUser class
            case VENDOR:
                return new Vendor("", "", "", ""); // Replace with your actual VendorUser class
            default:
                throw new IllegalArgumentException("Unsupported user type: " + userType);
        }
    }

    public static GenericUser<String> fromJson(JsonElement jsonElement) {

        UserType userType = UserType.USER;
        if (jsonElement.getAsJsonObject().has("userType")) {
            userType = gson.fromJson(jsonElement.getAsJsonObject().get("userType"), UserType.class);
        }

        GenericUser<String> user = createUser(userType);
        return gson.fromJson(jsonElement, user.getClass());
    }
}
