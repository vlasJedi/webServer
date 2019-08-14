package com.webApp.addPack;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RootHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Requested URL: " + httpExchange.getRequestURI());
        //if ()
        String basePath = "/home/vlas/IdeaProjects/webServer/src/main/webapp/WEB-INF/views/";
        Path rootHTMLPath = Paths.get(basePath, "index.html");
        MyFileReader fileReader = new MyFileReader("rootIndexHtml", rootHTMLPath.toString());

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
    }
}
