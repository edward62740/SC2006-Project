package com.nutriroute.controllers;

import static com.nutriroute.enums.UserType.VENDOR;

import android.widget.Toast;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;
import com.nutriroute.interfaces.IRequestMailboxService;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.models.RestaurantRequest;
import com.nutriroute.models.Vendor;
import com.nutriroute.services.RequestMailboxService;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminController {

    private static final IRequestMailboxService requestMailboxService = new RequestMailboxService();

    private static final IDataStore<String> dataStore = ServiceLocator.getDB();

    private AdminController(){
        requestMailboxService.synchronize();
    }

    public static List<Request<String>> getRestaurantRequests() {
        //requestMailboxService.synchronize();
        List<Request<String>> requests = new ArrayList<>();
        requests.addAll(requestMailboxService.receive(RequestType.CLAIM_REQUEST));
        requests.addAll(requestMailboxService.receive(RequestType.RESTAURANT_CHANGE_REQUEST));
        // filter by status
        requests.removeIf(request -> request.getStatus() != RequestStatus.PENDING);
        return requests;
    }

    public static List<Request<String>> getMenuChangeRequests() {
       // requestMailboxService.synchronize();
        List<Request<String>> requests = requestMailboxService.receive(RequestType.MENU_CHANGE_REQUEST);
        requests.removeIf(request -> request.getStatus() != RequestStatus.PENDING);
        return requests;
    }

    public static List<Request<String>> getRestaurantChangeRequests() {
        return requestMailboxService.receive(RequestType.RESTAURANT_CHANGE_REQUEST);
    }

    //probably need some logic to remove from the mailbox or delete the underlying obj from the db
    // if the request has been viewed or acked by the vendor (after approval or denial)
    public static void acceptRequest(Request<String> request) {
        request.setStatus(RequestStatus.APPROVED);
        dataStore.setRequest(request, request.getId());
        String vid = request.getVendorId(); // common to superclass
        if (dataStore.getUser(vid) == null || dataStore.getUser(vid).getUserType() != VENDOR)
            // expect short circuit evaluation
            //so theres no nullptr
        {
            Toast.makeText(null, "Vendor does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        Vendor vendor = (Vendor) (dataStore.getUser(vid)); // now valid cast

        if(request.getType().equals(RequestType.CLAIM_REQUEST)){
            RestaurantRequest restaurantRequest = (RestaurantRequest) request;
                Restaurant restaurant = restaurantRequest.getNewValue();
                restaurant.setId(restaurant.getName()); // this is not great, but we have to otherwise sync id generation
                if (restaurant.getId() != null && dataStore.getRestaurant(restaurant.getId()) == null) { // set iff does not exist
                    dataStore.setRestaurant(restaurant);
                    vendor.addRestaurant(restaurant.getId());
                    dataStore.setUser(vendor, vendor.getId());
                }
        }
        else if (request.getType().equals(RequestType.RESTAURANT_CHANGE_REQUEST)){
            RestaurantRequest restaurantRequest = (RestaurantRequest) request;
            Restaurant restaurant = restaurantRequest.getNewValue();
            dataStore.setRestaurant(restaurant); // set always and assume id exists in vendor
        }
        else if (request.getType().equals(RequestType.MENU_CHANGE_REQUEST)){
            MenuRequest menuRequest = (MenuRequest) request;
            System.out.println(menuRequest.getNewValue());
            System.out.println(menuRequest.getNewValue().getPrice());
            //seems like using id as index, ok.
            if(Objects.equals(menuRequest.getChangeType(), "add"))
            {
                dataStore.getRestaurant(menuRequest.getRestaurantId()).getMenu().addItem(menuRequest.getNewValue()); //expect java impl to overwrite
                dataStore.setRestaurant(dataStore.getRestaurant(menuRequest.getRestaurantId()));
            }
            else if(Objects.equals(menuRequest.getChangeType(), "update"))
            {
                String index = menuRequest.getMenuItemID(); // more like the index
                dataStore.getRestaurant(menuRequest.getRestaurantId()).getMenu().set(Integer.parseInt(index), menuRequest.getNewValue());
                dataStore.setRestaurant(dataStore.getRestaurant(menuRequest.getRestaurantId()));
            }

        }
        //requestMailboxService.synchronize();
    }

    public static void rejectRequest(Request<String> request) {
        request.setStatus(RequestStatus.DENIED);
        dataStore.setRequest(request, request.getId());
        //requestMailboxService.synchronize();
    }

    public static void logoutAndExit() {
        AuthController.logout();
    }
}
