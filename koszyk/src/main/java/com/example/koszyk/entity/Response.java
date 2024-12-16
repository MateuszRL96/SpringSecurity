package com.example.koszyk.entity;

import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class Response {

    private final String timestamp;
    private final String message;

    public Response(String timestamp) {
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        this.message = message;
    }
}
