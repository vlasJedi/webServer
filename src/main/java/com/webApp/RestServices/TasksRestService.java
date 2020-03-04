package com.webApp.RestServices;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.addPack.HttpUtils;
import com.webApp.addPack.SuperArray;
import com.webApp.repos.PersistenceManager;
import com.webApp.repos.TaskRepo.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TasksRestService implements HttpHandler{
    public void handle(HttpExchange httpExchange) {
        System.out.println("Requested URL: " + httpExchange.getRequestURI());
        OutputStream responseBody = httpExchange.getResponseBody();
        byte[][] response = null;
        long contentLength = 0;
        response = new byte[1][];
        response[0] = taskRestService(httpExchange.getRequestMethod(), httpExchange.getRequestURI(), httpExchange.getRequestBody());
        contentLength = response[0].length;
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
            System.out.println("*** TasksRestService:handle " + e.toString());
        }


        //fileReader.close();
        //bufferedWriter.close();
        httpExchange.close();
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
                List<Task> tasksRecords = em.createQuery("SELECT obj FROM Tasks obj", Task.class).getResultList();
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
