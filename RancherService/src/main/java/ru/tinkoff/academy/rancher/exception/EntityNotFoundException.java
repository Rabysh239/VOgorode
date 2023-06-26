package ru.tinkoff.academy.rancher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public EntityNotFoundException(String name, String id) {
        super(HttpStatus.NOT_FOUND, String.format("Not found %s with id: %s", name, id));
    }
}
