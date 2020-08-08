package com.webApp.server;

import com.sun.net.httpserver.HttpServer;
import com.webApp.RestServices.RoutesRestService;
import com.webApp.RestServices.UsersRestService;
import com.webApp.repos.RouteRepo.RouteService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class AppServer {
    public static void start() {
        try {
            InetSocketAddress isa = new InetSocketAddress("localhost", 8083);
            HttpServer httpServer = HttpServer.create(isa,0);
            //httpServer.createContext("/", new RootHandler());
            httpServer.createContext("/routes", new RoutesRestService());
            //httpServer.createContext("/categories", new CategoriesRestService());
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
