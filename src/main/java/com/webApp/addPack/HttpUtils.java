package com.webApp.addPack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtils {
    public enum HTTP_STATUSES {
        SUCCESS("SUCCESS", 200),
        NOT_FOUND("NOT_FOUND", 404);
        private String statusString;
        private int statusCode;
        HTTP_STATUSES(String statusString, int statusCode) {
            this.statusString = statusString;
            this.statusCode = statusCode;
        }
        public int getStatusCode() {
            return statusCode;
        }
        public String toString() {
            return statusString;
        }

    }
    public static Map<String, String> getParsedRequestMultiPartBody(byte[] requestBody) {
        /*
                    ------WebKitFormBoundaryk6Ss7BvVlBRpm8J6
                    Content-Disposition: form-data; name="taskName"

                    Infinitive
                            ------WebKitFormBoundaryk6Ss7BvVlBRpm8J6
                    Content-Disposition: form-data; name="taskDesc"

                    choose correct
                    ------WebKitFormBoundaryk6Ss7BvVlBRpm8J6
                    Content-Disposition: form-data; name="taskSubmit"

                    Submit
                            ------WebKitFormBoundaryk6Ss7BvVlBRpm8J6-- */
        String requestBodyString = new String(requestBody, StandardCharsets.UTF_8);
        int boundaryEnd = requestBodyString.indexOf("\n");
        String boundary = requestBodyString.substring(0, boundaryEnd);
        // .asList returns fixedsize list, so need to add it to another one
        List<String> formParts = new ArrayList<>(Arrays.asList(requestBodyString.split(boundary)));
        formParts.remove(0);
        Map<String, String> requestMap = new HashMap<>();
                    /*formParts.iterator().forEachRemaining(formPart -> {
                        String key, value;
                        Matcher keyMatcher = Pattern. compile("\"(.+)\"").matcher(formPart);
                        key = keyMatcher.find() ? keyMatcher.group(1) : null;
                        Matcher valueMatcher = Pattern. compile("\n(\r)?\n.+$").matcher(formPart);
                        value = valueMatcher.find() ? valueMatcher.group(1) : null;
                        requestMap.put(key, value);
                        key = value = null;
                    });*/
        formParts.iterator().forEachRemaining(new Consumer<String>() {
            public void accept(String formPart) {
                String key, value;
                Matcher keyMatcher = Pattern. compile("\"(.+)\"").matcher(formPart);
                key = keyMatcher.find() ? keyMatcher.group(1) : null;
                Matcher valueMatcher = Pattern. compile("\n(\r)?\n(.+)(\r)?\n").matcher(formPart);
                value = valueMatcher.find() ? valueMatcher.group(2) : null;
                requestMap.put(key, value);
                key = value = null;
            }
        });

        return requestMap;
    }

    public static Map<String, String> getMapDataFromRequestBody(InputStream os) {
        try {
            byte[] bodyBytes = new byte[os.available()];
            os.read(bodyBytes);
            os.close();
            return getParsedRequestMultiPartBody(bodyBytes);
        } catch(IOException e) {
            System.out.println("Could not get map of data from request body");
            return null;
        }
    }

    public String getHashFrom(String string, boolean withSignKey, String algo) {
        if (algo == null || algo.isEmpty()) algo = "MD5";
        MessageDigest md = null;
        String finalString = null;
        try {
            md = MessageDigest.getInstance(algo);
            md.update(new String(withSignKey ? string + "someKey" : string).getBytes(StandardCharsets.UTF_8));
            finalString = new String(md.digest(), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {}
        return finalString;
    }
}
