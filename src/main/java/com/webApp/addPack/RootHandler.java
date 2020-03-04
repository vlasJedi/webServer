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
            case "/tasks":
                response = new byte[1][];
                response[0] = taskRestService(httpExchange.getRequestMethod(), httpExchange.getRequestURI(), httpExchange.getRequestBody());
                contentLength = response[0].length;
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
        httpExchange.sendResponseHeaders(200, contentLength);
        if (response == null) {
            System.out.println("*** Response body is empty");
        } else {
            for (byte[] responseBytes : response) {
                outputStream.write(responseBytes);
            }
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

    private byte[] taskRestService(String method, URI requestURI, InputStream requestBodyStream) {
        byte[] response = null;
        int requestBodyLength = 0;
        EntityManager em = null;
        EntityTransaction transaction = null;
        switch (method) {
            case "GET":
                em = PersistenceManager.getEntityManager();
                transaction = em.getTransaction();
                transaction.begin();
                List<Task> tasksRecords = em.createQuery("SELECT obj FROM Task obj", Task.class).getResultList();
                StringBuilder tasksNames = new StringBuilder("");
                tasksRecords.listIterator().forEachRemaining(task -> {
                    tasksNames.append("Task name is: ").append(task.getName()).append("\r\n");
                });
                transaction.commit();
                em.close();
                response  = tasksNames.toString().getBytes(StandardCharsets.UTF_8);
                break;
            case "POST":
                em = PersistenceManager.getEntityManager();
                transaction = em.getTransaction();
                transaction.begin();
                try {
                    byte[] bodyBytes = new byte[requestBodyStream.available()];
                    requestBodyLength = requestBodyStream.read(bodyBytes);
                    Map<String, String> requestMap = HttpUtils.getParsedRequestMultiPartBody(bodyBytes);
                    em = PersistenceManager.getEntityManager();
                    transaction = em.getTransaction();
                    transaction.begin();
                    Task task = new Task(requestMap.get("taskName"), requestMap.get("tasDesc"));
                    em.persist(task);
                    em.flush();
                    transaction.commit();
                    em.close();
                } catch (IOException e) {
                    System.out.println("*** " + e.toString());
                }
        }
        return response;
    }
}
