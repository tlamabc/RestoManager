package com.droidfreshsquad.poly2023.Fragment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
    private String message;
    public String sender;
    String content;
    private String timestamp;

    public Message(String message) {
        this.message = message;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
