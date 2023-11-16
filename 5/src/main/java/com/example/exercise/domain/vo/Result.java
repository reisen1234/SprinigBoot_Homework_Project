package com.example.exercise.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private String statusCode;
    private String message;
    private T data;

    public Result(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
