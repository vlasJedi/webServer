package com.webApp.RestServices;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.repos.CategoryRepo.Category;
import com.webApp.repos.CategoryRepo.CategoryService;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class CategoriesRestService implements HttpHandler {
    private String method;
    private URI uri;
    public void handle(HttpExchange exchange) {
        method = exchange.getRequestMethod();
        uri = exchange.getRequestURI();
        System.out.println("CategoriesRestService:handle > " + method + " URI: " + uri.toString());
        byte[] responseBytes = getResponseBytes();
        try {
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseBody().write(responseBytes);
        } catch (IOException e) {
            System.out.println("CategoriesRestService:handle > Error to send response");
        }

        exchange.close();

    }

    public byte[] getResponseBytes() {
        Set<Category> categories = CategoryService.getAllCategories();
        String categoriesJson = createJsonArray(categories);
        byte[] categoriesJsonBytes = categoriesJson.getBytes(StandardCharsets.UTF_8);
        return categoriesJsonBytes;
    }

    public static String createJsonArray(Set<Category> categories) {
        StringBuilder result = new StringBuilder("{[");
        categories.iterator().forEachRemaining(category -> result.append(category.toJson()).append(","));
        result.append("]}");
        return result.toString();
    }
}
