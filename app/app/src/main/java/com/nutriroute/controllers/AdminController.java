package com.nutriroute.controllers;

import java.util.List;
import com.nutriroute.models.Claim;
import com.nutriroute.models.MenuRequest;
import com.nutriroute.services.ClaimService;
import com.nutriroute.services.MenuRequestService;

public class AdminController {


    // Fetch pending claims
    public List<Claim> getPendingClaims() {
        return ClaimService.getPendingClaims();
    }

    // Accept a specific claim
    public void acceptClaim(String claimId) {
        Claim claim = findClaimById(claimId);
        if (claim != null) {
            ClaimService.acceptClaim(claim);
            // Logic to notify the vendor about the acceptance
        }
    }

    // Reject a specific claim
    public void rejectClaim(String claimId, String reason) {
        Claim claim = findClaimById(claimId);
        if (claim != null) {
            ClaimService.rejectClaim(claim, reason);
            // Logic to notify the vendor about the rejection and the reason
        }
    }

    // Fetch pending menu change requests
    public List<MenuRequest> getPendingMenuRequests() {
        return MenuRequestService.getPendingRequests();
    }

    // Accept a specific menu request
    public void acceptMenuRequest(String requestId) {
        MenuRequest request = findMenuRequestById(requestId);
        if (request != null) {
            MenuRequestService.acceptMenuRequest(request);
            // Logic to apply the changes to the menu
        }
    }

    // Reject a specific menu request
    public void rejectMenuRequest(String requestId, String reason) {
        MenuRequest request = findMenuRequestById(requestId);
        if (request != null) {
            MenuRequestService.rejectMenuRequest(request, reason);
            // Logic to notify the vendor about the rejection and the reason
        }
    }

    // Helper methods to find specific claims and menu requests
    private Claim findClaimById(String claimId) {
        for (Claim claim : ClaimService.getPendingClaims()) {
            if (claim.getId().equals(claimId)) {
                return claim;
            }
        }
        return null;
    }

    private MenuRequest findMenuRequestById(String requestId) {
        for (MenuRequest request : MenuRequestService.getPendingRequests()) {
            if (request.getId().equals(requestId)) {
                return request;
            }
        }
        return null;
    }
}