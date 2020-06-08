package com.webApp.server;

import com.sun.net.httpserver.HttpServer;
import com.webApp.RestServices.CategoriesRestService;
import com.webApp.RestServices.TasksRestService;
import com.webApp.RestServices.UsersRestService;
import com.webApp.addPack.RootHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class AppServer {
    public static void start() {
        try {
            InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
            HttpServer httpServer = HttpServer.create(isa,0);
            httpServer.createContext("/", new RootHandler());
            httpServer.createContext("/tasks", new TasksRestService());
            httpServer.createContext("/categories", new CategoriesRestService());
            httpServer.createContext("/users", new UsersRestService());
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve ip/hostname");
        } catch (IOException e) {
            System.out.println("Could not IOException");
        }
    }
}