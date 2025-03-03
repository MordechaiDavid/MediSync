package com.medisync.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private long timestamp;
    private String errorDetails;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(int status, String message, String errorDetails) {
        this.status = status;
        this.message = message;
        this.errorDetails = errorDetails;
        this.timestamp = System.currentTimeMillis();
    }

}
