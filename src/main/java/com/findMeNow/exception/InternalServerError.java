package com.findMeNow.exception;

public class InternalServerError extends Exception {


    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String msg, Throwable cause) {
        super(msg, cause);
    }

    public Integer getCode() {
        return 500;
    }

    public String getDescription() {
        return "Internal server error";
    }
}
