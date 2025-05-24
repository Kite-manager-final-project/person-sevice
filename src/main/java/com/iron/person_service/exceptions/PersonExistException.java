package com.iron.person_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PersonExistException extends RuntimeException {
    public PersonExistException(String message) {
        super(message);
    }
}
