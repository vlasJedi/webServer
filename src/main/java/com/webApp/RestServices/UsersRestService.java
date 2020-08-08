package com.webApp.RestServices;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.addPack.HttpUtils;
import com.webApp.controllers.UsersController;
import com.webApp.repos.UserRepo.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UsersRestService implements HttpHandler {
    public void handle(HttpExchange httpExchange) {
        String responseText = "";
        int statusCode = 200;
        switch (httpExchange.getRequestMethod()) {
            case "POST":
                Map<String, String> userData = HttpUtils.getMapDataFromRequestBody(httpExchange.getRequestBody());
                User user = new User();
                user.setName(userData.get("username"));
                user.setPassword(userData.get("password"));
                user.setMail(userData.get("mail"));
                if (!UsersController.validateUser(user)) {
                    responseText = "Such user can not be created";
                    statusCode = 409;
                    System.out.println(responseText);
                } else {
                    responseText = "Trying to create new user";
                    System.out.println(responseText);
                    if (!UsersController.createUser(user)) {
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
        } catch (IOException e) {}
    }
}
