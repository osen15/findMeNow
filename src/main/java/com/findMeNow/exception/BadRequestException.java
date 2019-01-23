package com.findMeNow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class BadRequestException extends NumberFormatException{

    public BadRequestException(String message) {
        super(message);
    }

    public Integer getCode() {
        return 400;
    }

    public String getDescription() {
        return "Bad request exception";
    }
}
