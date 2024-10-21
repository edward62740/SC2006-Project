package com.nutriroute.utils;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.nutriroute.enums.UserType;
import com.nutriroute.models.Admin;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.User;
import com.nutriroute.models.Vendor;

public class GenericUserFactory {

    private static final Gson gson = ServiceLocator.getDB().getGson();

    public static GenericUser<String> createUser(UserType userType) {
        switch (userType) {
            case ADMIN:
                return new Admin("", "", "", "");
            case USER:
                return new User("", "", "", "");
            case VENDOR:
                return new Vendor("", "", "", "");
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
