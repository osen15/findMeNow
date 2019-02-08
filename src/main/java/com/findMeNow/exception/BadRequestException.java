package com.findMeNow.exception;



public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);
    }

    public Integer getCode() {
        return 400;
    }

    public String getDescription() {
        return "Bad request";
    }
}
