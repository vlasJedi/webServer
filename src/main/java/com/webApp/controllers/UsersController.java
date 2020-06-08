package com.webApp.controllers;

import com.webApp.repos.UserRepo.User;
import com.webApp.repos.UserRepo.UsersService;

public class UsersController {
    public static boolean createUser(User user) {
        if (!validateUser(user)) {
            return false;
        }
        return UsersService.createUser(user);
    }

    public static boolean validateUser(User user) {
        return UsersService.isValidUser(user);
    }
}
