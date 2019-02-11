package com.findMeNow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request")
@SuppressWarnings("serial")
public class BadRequestException extends Exception {
    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
