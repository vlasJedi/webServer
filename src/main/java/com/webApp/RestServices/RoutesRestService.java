package com.webApp.RestServices;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.repos.RouteRepo.Route;
import com.webApp.repos.RouteRepo.RouteService;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoutesRestService implements HttpHandler {
    private String method;
    private URI uri;
    public void handle(HttpExchange exchange) {
        method = exchange.getRequestMethod();
        uri = exchange.getRequestURI();
        System.out.println("RoutesRestService:handle > " + method + " URI: " + uri.toString());
        byte[] responseBytes = null;
        try {
            switch (method) {
                default:
                    responseBytes = handleReadRequest(uri.getPath()) == null ? new byte[]{} : handleReadRequest(uri.getPath());
            }
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseBody().write(responseBytes);
        } catch (IOException e) {
            System.out.println("RoutesRestService:handle > Error to send response");
        }

        exchange.close();

    }

    public static byte[] readAllRoutes() {
        Set<Route> routes = RouteService.get().getAllRoutes();
        String routesJson = createJsonArray(routes);
        return routesJson.getBytes(StandardCharsets.UTF_8);
    }

    public static String createJsonArray(Set<Route> routes) {
        StringBuilder result = new StringBuilder("{[");
        routes.iterator().forEachRemaining(route -> result.append(route.toJson()).append(","));
        result.append("]}");
        return result.toString();
    }

    public static byte[] handleReadRequest(String path) {
        List<String> pathComps = Arrays.stream(path.split("/", 0))
                .filter(part -> !part.isEmpty()).collect(Collectors.toList());
        if (pathComps.size() != 1) {
            System.out.println("handleReadRequest: not correct path, too long to proceed");
            return null;
        }
        return readAllRoutes();
    }
}
