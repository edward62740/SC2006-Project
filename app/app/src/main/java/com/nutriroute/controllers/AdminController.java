package com.nutriroute.controllers;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;
import com.nutriroute.interfaces.IRequestMailboxService;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;
import com.nutriroute.services.RequestMailboxService;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

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
        return requests;
    }

    public static List<Request<String>> getMenuChangeRequests() {
       // requestMailboxService.synchronize();
        return requestMailboxService.receive(RequestType.MENU_CHANGE_REQUEST);
    }

    public static List<Request<String>> getRestaurantChangeRequests() {
        return requestMailboxService.receive(RequestType.RESTAURANT_CHANGE_REQUEST);
    }

    //probably need some logic to remove from the mailbox or delete the underlying obj from the db
    // if the request has been viewed or acked by the vendor (after approval or denial)
    public static void acceptRequest(Request<String> request) {
        request.setStatus(RequestStatus.APPROVED);
        dataStore.setRequest(request, request.getId());
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
