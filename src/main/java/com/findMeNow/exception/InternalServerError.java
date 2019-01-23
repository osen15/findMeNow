package com.findMeNow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
public class InternalServerError extends RuntimeException {

    public InternalServerError(String message) {
        super(message);
    }

    public Integer getCode() {
        return 500;
    }

    public String getDescription() {
        return "Internal server error";
    }
}
