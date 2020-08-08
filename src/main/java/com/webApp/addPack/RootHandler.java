/*
package com.webApp.addPack;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.repos.PersistenceManager;
import com.webApp.repos.TaskRepo.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RootHandler implements HttpHandler {
    public static final String BASE_FILE_PATH = "src/main/webapp/WEB-INF/views/mainEntry";
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Requested URL: " + httpExchange.getRequestURI());
        OutputStream responseBody = httpExchange.getResponseBody();
        byte[][] response = null;
        SuperArray superArray = null;
        long contentLength = 0;
        int statusCode = 500;
        FileInputStream fis;
        switch (httpExchange.getRequestURI().toString()) {
            case "/":
                if ((fis = getFileInStreamForThePath("/index.html")) == null) {
                    String errorMsg = HttpUtils.HTTP_STATUSES.NOT_FOUND.toString();
                    statusCode = HttpUtils.HTTP_STATUSES.NOT_FOUND.getStatusCode();
                    System.out.println(errorMsg);
                    response = new byte[1][];
                    response[0] = errorMsg.getBytes(StandardCharsets.UTF_8);
                    contentLength = response[0].length;
                    break;
                }
                superArray = new SuperArray();
                response = superArray.addBytesFromFileInput(fis).getData();
                contentLength = superArray.getTotalDataSize();
                statusCode = HttpUtils.HTTP_STATUSES.SUCCESS.getStatusCode();
                break;
            default:
                if ((fis = getFileInStreamForThePath(httpExchange.getRequestURI().getPath())) == null) {
                    String errorMsg = HttpUtils.HTTP_STATUSES.NOT_FOUND.toString();
                    statusCode = HttpUtils.HTTP_STATUSES.NOT_FOUND.getStatusCode();
                    System.out.println(errorMsg);
                    response = new byte[1][];
                    response[0] = errorMsg.getBytes(StandardCharsets.UTF_8);
                    contentLength = response[0].length;
                    break;
                }
                superArray = new SuperArray();
                response = superArray.addBytesFromFileInput(fis).getData();
                contentLength = superArray.getTotalDataSize();
                statusCode = HttpUtils.HTTP_STATUSES.SUCCESS.getStatusCode();
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        */
/*PrintWriter writerOutputStream = new PrintWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter( writerOutputStream );
        String fileTextContent = fileReader.getFileTextContent();*//*

        */
/*if ( fileTextContent == null ) {
            System.out.println("RootHandler|handle(): " + "fileTextContent is null");
            fileTextContent = "Error 503: could not get fileTextContent for the url";
        }
        bufferedWriter.write( fileTextContent );*//*

        try {
            if (response == null) {
                httpExchange.sendResponseHeaders(statusCode, contentLength);
                System.out.println("*** Response body is empty");
            } else {
                httpExchange.sendResponseHeaders(statusCode, contentLength);
                for (byte[] responseBytes : response) {
                    outputStream.write(responseBytes);
                }
            }
            outputStream.close();
        } catch (IOException e) {
            System.out.println("*** RootHandle:handle " + e.toString());
        }
        //fileReader.close();
        //bufferedWriter.close();
        httpExchange.close();
    }

    private FileInputStream getFileInStreamForThePath(String path) {
        File file = Paths.get(BASE_FILE_PATH, path).toAbsolutePath().toFile();
        if (!file.exists()) {
            System.out.println("*** The file does not exist: " + file.getAbsolutePath());
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e){
            System.out.println("*** File not found");
        }
        return fis;
    }

}
*/
