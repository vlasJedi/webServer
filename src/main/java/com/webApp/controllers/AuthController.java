package com.webApp.controllers;

public class AuthController {
    public static boolean isUserValidForAuth(String username, String password, String mail) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty()
            && mail != null && !mail.isEmpty();
    }

    public static String authUser(String username, String password) {
        if (!UsersController.doesUserExist(username)) {
            System.out.printf("Such user %s does not exist, can not be auth", username);
            return null;
        }
        if (password == null || password.isEmpty()) {
            System.out.printf("Provide password for the user %s", username);
            return null;
        }
        return null;
    }
}
