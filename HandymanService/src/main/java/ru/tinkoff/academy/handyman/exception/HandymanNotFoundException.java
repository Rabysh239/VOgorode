package ru.tinkoff.academy.handyman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class HandymanNotFoundException extends ResponseStatusException {

    public HandymanNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Not found handyman with id: " + id);
    }
}
