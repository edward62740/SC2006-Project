package com.nutriroute.models;

import com.nutriroute.enums.UserType;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends GenericUser<String> {
    private static boolean isLoggedIn = false;
    private static ArrayList<Menu> pendingMenuUpdates = new ArrayList<>(); // List for menu updates
    private static ArrayList<Restaurant> pendingClaims = new ArrayList<>(); // List for restaurant claims

    public Admin(String name, String email, String password, String id) {
        super(name, email, password, id, UserType.ADMIN);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Simulating login for demonstration purposes
        isLoggedIn = true; // Assume the admin has logged in for testing

        while (isLoggedIn) {
            displayMenu(scanner);
        }

        scanner.close();
    }

    private static void displayMenu(Scanner scanner) {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Review menu changes");
        System.out.println("2. Review restaurant claims");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                ReviewMenuUpdate(scanner);
                break;
            case 2:
                ReviewRestaurantClaim(scanner);
                break;
            case 3:
                System.out.println("Exiting the application.");
                isLoggedIn = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void ReviewMenuUpdate(Scanner scanner) {
        if (pendingMenuUpdates.isEmpty()) {
            System.out.println("No pending menu update requests.");
            return;
        }

        System.out.println("\nPending Menu Update Requests:");
        for (int i = 0; i < pendingMenuUpdates.size(); i++) {
            System.out.println((i + 1) + ". " + pendingMenuUpdates.get(i).getSummary());
        }

        System.out.print("Select a request to review (or press 0 to exit): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0) {
            return; // Exit option
        } else if (choice < 1 || choice > pendingMenuUpdates.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        MenuUpdateRequest selectedRequest = pendingMenuUpdates.get(choice - 1);
        displayMenuRequestDetails(selectedRequest);

        System.out.print("Do you want to accept this request (yes/no)? ");
        String response = scanner.nextLine();

        if ("yes".equalsIgnoreCase(response)) {
            acceptMenuUpdateRequest(selectedRequest);
        } else if ("no".equalsIgnoreCase(response) || response.isEmpty()) {
            rejectMenuUpdateRequest(selectedRequest);
        } else {
            System.out.println("Invalid response. Request not processed.");
        }
    }

    private static void displayMenuRequestDetails(MenuUpdateRequest request) {
        System.out.println("\nRequest Details:");
        System.out.println("Summary: " + request.getSummary());
        System.out.println("Vendor: " + request.getVendor());
        System.out.println("Menu Items:");
        for (MenuItem item : request.getMenuItems()) {
            System.out.println("- " + item.getName() + " (Calories: " + item.getCalories() + ")");
        }
    }

    private static void acceptMenuUpdateRequest(MenuUpdateRequest request) {
        // Logic to assign the menu contents to the restaurant
        System.out.println("Request accepted. Menu contents assigned to " + request.getVendor() + ".");
        pendingMenuUpdates.remove(request); // Remove the request from pending list
    }

    private static void rejectMenuUpdateRequest(MenuUpdateRequest request) {
        // Logic to notify the vendor about the rejection
        System.out.println("Request rejected. Notifying vendor: " + request.getVendor());
        pendingMenuUpdates.remove(request); // Remove the request from pending list
    }

    private static void ReviewRestaurantClaim(Scanner scanner) {
        if (pendingClaims.isEmpty()) {
            System.out.println("No pending restaurant claims.");
            return;
        }

        System.out.println("\nPending Restaurant Claims:");
        for (int i = 0; i < pendingClaims.size(); i++) {
            System.out.println((i + 1) + ". " + pendingClaims.get(i).getSummary());
        }

        System.out.print("Select a claim to review (or press 0 to exit): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0) {
            return; // Exit option
        } else if (choice < 1 || choice > pendingClaims.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        RestaurantClaim selectedClaim = pendingClaims.get(choice - 1);
        displayClaimDetails(selectedClaim);

        System.out.print("Do you want to accept this claim (yes/no)? ");
        String response = scanner.nextLine();

        if ("yes".equalsIgnoreCase(response)) {
            acceptClaim(selectedClaim);
        } else if ("no".equalsIgnoreCase(response) || response.isEmpty()) {
            rejectClaim(selectedClaim);
        } else {
            System.out.println("Invalid response. Claim not processed.");
        }
    }

    private static void displayClaimDetails(RestaurantClaim claim) {
        System.out.println("\nClaim Details:");
        System.out.println("Summary: " + claim.getSummary());
        System.out.println("Vendor: " + claim.getVendor());
        System.out.println("Evidence: " + claim.getEvidence());
        // Additional logic to display evidence (images, PDFs) can be added here
    }

    private static void acceptClaim(RestaurantClaim claim) {
        // Logic to assign the restaurant to the vendor
        System.out.println("Claim accepted. Restaurant assigned to " + claim.getVendor() + ".");
        pendingClaims.remove(claim); // Remove the claim from pending list
    }

    private static void rejectClaim(RestaurantClaim claim) {
        // Logic to notify the vendor about the rejection
        System.out.println("Claim rejected. Notifying vendor: " + claim.getVendor());
        pendingClaims.remove(claim); // Remove the claim from pending list
    }

    // Dummy methods to add data for testing
    public static void addMenuUpdateRequest(MenuUpdateRequest request) {
        pendingMenuUpdates.add(request);
    }

    public static void addRestaurantClaim(RestaurantClaim claim) {
        pendingClaims.add(claim);
    }
}
