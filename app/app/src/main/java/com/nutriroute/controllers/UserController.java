package com.nutriroute.controllers;

public class UserController {

    private UserController() {
    }

    // entry point for user controller
    public static void run() {

        // get some signal from the GUI
        /*

        decompose task
        1. there are stuff to trigger the gui to show details (1-8, 1-9)
        2. there is the restaurant/user interaction (1-3, 1-4, 1-5, 1-6)
        3. mutate the user (1-1)

        define: IUserMapService, call maps and draw etc. (API: google maps)
        IUserFoodService, do the logic for calorie provisioning etc.
        IUserManagementService, create or modify obj and dump into DB (API: DB)

        1-2,3,4,5 requires: find N minimum dist restaurants,
        calculate calories and subtract from user's daily intake,
        the google maps stuff + overlay coordinates on map
        probably on view side: open the restaurant details when clicked

        1-6 is just a user modification, so it's a simple call to the DB

        1-7 is a notification thing

        1-8,9 is a list of calories. possible to use the user storage to get the calorie history
        no need to bother with the DB for this
         */

    }


}
