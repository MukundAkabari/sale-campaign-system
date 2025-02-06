package com.example.sale_campaign_system.Model;

import org.springframework.http.HttpStatus;

public class ResponseDTO <T>{
    private T body;
    private String message;
    private HttpStatus status;


    public ResponseDTO(T body, String message, HttpStatus status) {
        this.body = body;
        this.message = message;
        this.status = status;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
