package com.webApp.addPack;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webApp.repos.PersistenceManager;
import com.webApp.repos.TaskRepo.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RootHandler implements HttpHandler {
    public static final String BASE_FILE_PATH = "src/main/webapp/WEB-INF/views/mainEntry";
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Requested URL: " + httpExchange.getRequestURI());
        String response = "error occured";
        switch (httpExchange.getRequestURI().toString()) {
            case "/":
                response = getFileOutStreamForThePath("/index.html");
                break;
            case "/tasks":
                response = getTasksServiceOutStreamResponse(httpExchange.getRequestMethod(),
                        httpExchange.getRequestBody(), httpExchange.getRequestURI());
                break;
            default:
                response = getFileOutStreamForThePath(httpExchange.getRequestURI().getPath());
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        PrintWriter writerOutputStream = new PrintWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter( writerOutputStream );
        String fileTextContent = fileReader.getFileTextContent();
        if ( fileTextContent == null ) {
            System.out.println("RootHandler|handle(): " + "fileTextContent is null");
            fileTextContent = "Error 503: could not get fileTextContent for the url";
        }
        bufferedWriter.write( fileTextContent );
        httpExchange.sendResponseHeaders(200, fileTextContent.length());
        fileReader.close();
        bufferedWriter.close();
        httpExchange.close();
    }

    private String getFileOutStreamForThePath(String path) {
        File file = Paths.get(BASE_FILE_PATH, path).toAbsolutePath().toFile();
        if (!file.exists()) System.out.println("*** The file does not exist: " + file.getAbsolutePath());
        return (new MyFileReader(file)).getFileTextContent();
    }

    private String getTasksServiceOutStreamResponse(String method, InputStream requestBody
            , URI requestURI) {
        switch (method) {
            case "GET":
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                List<Task> tasksRecords = em.createQuery("SELECT obj FROM Task obj", Task.class).getResultList();
                String tasksNames = "";

                tasksRecords.listIterator().forEachRemaining(task -> {
                    tasksNames += "Task name is: " + task.getName() + "\r\n";
                });
                transaction.commit();
                em.close();
        }

    }
}
