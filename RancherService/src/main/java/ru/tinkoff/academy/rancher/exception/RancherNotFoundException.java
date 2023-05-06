package ru.tinkoff.academy.rancher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RancherNotFoundException extends ResponseStatusException {

    public RancherNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Not found rancher with id: " + id);
    }
}
