package com.nutriroute.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.nutriroute.enums.RequestType;
import com.nutriroute.enums.UserType;
import com.nutriroute.models.GenericUser;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.RestaurantRequest;

public class RequestFactory {

    private static final Gson gson = ServiceLocator.getDB().getGson();

    public static Request<String> createRequest(RequestType requestType) {
        switch (requestType) {
            case CLAIM_REQUEST:
                return new RestaurantRequest("", "");
            case MENU_CHANGE_REQUEST:
                return new MenuRequest("", "");
            default:
                throw new IllegalArgumentException("Unsupported request type: " + requestType);
        }
    }

    public static Request<String> fromJson(JsonElement jsonElement) {

        RequestType reqType = RequestType.CLAIM_REQUEST;
        if (jsonElement.getAsJsonObject().has("type")) {
            reqType = gson.fromJson(jsonElement.getAsJsonObject().get("type"), RequestType.class);
        }

        Request<String> req = createRequest(reqType);
        return gson.fromJson(jsonElement, req.getClass());
    }
}
