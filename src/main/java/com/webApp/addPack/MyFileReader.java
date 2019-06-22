package com.webApp.addPack;

import java.io.*;

public class MyFileReader {
    private String fileName = "";
    private File file = null;
    private InputStream inputStream = null;
    private Reader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private String fileTxtBuffer = "";
    public MyFileReader(String fileName, String filePath) {
        file = new File(filePath);
        try {
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException ex) {
            System.out.println("EXCEPTION: MyFileReader|constructor(): " + ex.getMessage());
        }
    }
    public String getLine() {
        String lineFromFile = null;
        try {
            lineFromFile = bufferedReader.readLine();
        } catch (IOException ex) {
            System.out.println("EXCEPTION: MyFileReader|getLine(): Could not read line from file: " + ex.getMessage());
        } finally {
            return lineFromFile;
        }
    }
    public String getFileTextContent() {
        String lineFromFile = "";
        try {
            while ( ( lineFromFile = getLine() ) != null ) {
                fileTxtBuffer += lineFromFile + "\r\n";
            }
        } catch ( RuntimeException ex ) {
            System.out.printf("EXCEPTION: Demo|main(): " + ex.getMessage());
        } finally {
            return fileTxtBuffer;
        }
    }
    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("MyFileReader: " + fileName + "|close(): " + "Could not close bufferedReader: " + ex.getMessage());
        }
    }
}
