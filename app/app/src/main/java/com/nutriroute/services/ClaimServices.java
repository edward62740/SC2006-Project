package com.nutriroute.services;

import com.nutriroute.models.Claim;
import com.nutriroute.models.MenuRequest;

import java.util.ArrayList;
import java.util.List;

public class ClaimService {
    private static List<Claim> pendingClaims = new ArrayList<>(); // List of pending claims
    private static List<MenuRequest> pendingRequests = new ArrayList<>(); // List of pending menu requests

    // Method to retrieve pending claims
    public static List<Claim> getPendingClaims() {
        return pendingClaims; // Return the list of pending claims
    }

    // Method to retrieve pending menu requests
    public static List<MenuRequest> getPendingRequests() {
        return pendingRequests; // Return the list of pending menu requests
    }

    // Method to accept a claim
    public static void acceptClaim(Claim claim) {
        claim.setStatus("accepted"); // Set claim status to accepted
        pendingClaims.remove(claim);  // Remove from pending claims
        // Additional logic for accepting the claim (e.g., updating the database) can be added here
    }

    // Method to reject a claim
    public static void rejectClaim(Claim claim, String reason) {
        claim.setStatus("rejected"); // Set claim status to rejected
        claim.setReason(reason);      // Set the reason for rejection
        pendingClaims.remove(claim);  // Remove from pending claims
        // Additional logic for rejecting the claim (e.g., updating the database) can be added here
    }

    // Method to accept a menu request
    public static void acceptRequest(MenuRequest request) {
        request.setStatus("accepted"); // Set request status to accepted
        pendingRequests.remove(request); // Remove from pending requests
        // Additional logic for accepting the request (e.g., updating the database) can be added here
    }

    // Method to reject a menu request
    public static void rejectRequest(MenuRequest request, String reason) {
        request.setStatus("rejected"); // Set request status to rejected
        request.setReason(reason);      // Set the reason for rejection
        pendingRequests.remove(request); // Remove from pending requests
        // Additional logic for rejecting the request (e.g., updating the database) can be added here
    }

    // Method to add a new claim to the pending list
    public static void addClaim(Claim claim) {
        pendingClaims.add(claim); // Add the new claim to the pending list
    }

    // Method to add a new menu request to the pending list
    public static void addRequest(MenuRequest request) {
        pendingRequests.add(request); // Add the new request to the pending list
    }
}
