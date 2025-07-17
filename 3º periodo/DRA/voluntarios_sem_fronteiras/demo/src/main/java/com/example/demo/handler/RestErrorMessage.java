package com.example.demo.handler;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    private int status;
    private String message;

    public RestErrorMessage(){}

    public RestErrorMessage(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
