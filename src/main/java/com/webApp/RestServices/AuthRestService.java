package com.webApp.RestServices;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.addPack.HttpUtils;
import com.webApp.controllers.AuthController;
import com.webApp.controllers.UsersController;
import com.webApp.repos.UserRepo.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AuthRestService implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        String responseText = "";
        int statusCode = 200;
        switch (httpExchange.getRequestMethod()) {
            case "POST":
                Map<String, String> userData = HttpUtils.getMapDataFromRequestBody(httpExchange.getRequestBody());

                String username = userData.get("username");
                String pass = userData.get("password");
                String mail = userData.get("mail");

                if (!AuthController.isUserValidForAuth(username, pass, mail)) {
                    responseText = "Such user can not be login";
                    statusCode = 409;
                    System.out.println(responseText);
                } else {
                    responseText = "Trying to auth user";
                    System.out.println(responseText);
                    if (!UsersController.createUser(new User(username, pass, mail))) {
                        System.out.println("User could not be created");
                    } else {
                        System.out.println("User created successfully");
                    }
                }
        }
        try {
            httpExchange.getRequestBody().close();
            httpExchange.sendResponseHeaders(statusCode, responseText.getBytes(StandardCharsets.UTF_8).length);
            httpExchange.getResponseBody().write(responseText.getBytes(StandardCharsets.UTF_8));
            httpExchange.getResponseBody().close();
            httpExchange.close();
        } catch (
                IOException e) {
        }
    }
}
