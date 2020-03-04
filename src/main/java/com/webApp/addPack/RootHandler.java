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
        switch (httpExchange.getRequestURI().toString()) {
            case "/":
                superArray = new SuperArray();
                response = superArray.addBytesFromFileInput(getFileInStreamForThePath("/index.html")).getData();
                contentLength = superArray.getTotalDataSize();
                break;
            default:
                superArray = new SuperArray();
                response = superArray.addBytesFromFileInput(getFileInStreamForThePath(httpExchange.getRequestURI()
                        .getPath())).getData();
                contentLength = superArray.getTotalDataSize();
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        /*PrintWriter writerOutputStream = new PrintWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter( writerOutputStream );
        String fileTextContent = fileReader.getFileTextContent();*/
        /*if ( fileTextContent == null ) {
            System.out.println("RootHandler|handle(): " + "fileTextContent is null");
            fileTextContent = "Error 503: could not get fileTextContent for the url";
        }
        bufferedWriter.write( fileTextContent );*/
        try {
            httpExchange.sendResponseHeaders(200, contentLength);
            if (response == null) {
                System.out.println("*** Response body is empty");
            } else {
                for (byte[] responseBytes : response) {
                    outputStream.write(responseBytes);
                }
                outputStream.close();
            }
        } catch (IOException e) {
            System.out.println("*** RootHandle:handle " + e.toString());
        }
        //fileReader.close();
        //bufferedWriter.close();
        httpExchange.close();
    }

    private FileInputStream getFileInStreamForThePath(String path) {
        File file = Paths.get(BASE_FILE_PATH, path).toAbsolutePath().toFile();
        if (!file.exists()) System.out.println("*** The file does not exist: " + file.getAbsolutePath());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e){
            System.out.println("*** File not found");
        }
        return fis;
    }

}
