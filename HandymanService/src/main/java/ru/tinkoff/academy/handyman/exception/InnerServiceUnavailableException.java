package ru.tinkoff.academy.handyman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InnerServiceUnavailableException extends ResponseStatusException {

    public InnerServiceUnavailableException() {
        super(HttpStatus.SERVICE_UNAVAILABLE, "Unable to connect to inner service");
    }
}