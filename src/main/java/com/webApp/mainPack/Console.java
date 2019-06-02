package com.webApp.mainPack;

public class Console {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void logMessage() {
        System.out.println("Message value from config xml is: " + this.getMessage());
    }
}
