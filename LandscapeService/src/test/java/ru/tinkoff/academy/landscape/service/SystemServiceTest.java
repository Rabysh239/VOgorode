package ru.tinkoff.academy.landscape.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.tinkoff.academy.landscape.ReadinessStatus.*;
import static ru.tinkoff.academy.landscape.service.SystemService.setIsMalfunction;
import static ru.tinkoff.academy.landscape.service.SystemService.setIsReady;

class SystemServiceTest {
    private final SystemService service = new SystemService();

    @Test
    void getStatusWhenNotReady() {
        setIsReady(false);
        setIsMalfunction(false);

        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenNotReadyMalfunction() {
        setIsReady(false);
        setIsMalfunction(true);

        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenReady() {
        setIsReady(true);
        setIsMalfunction(false);

        assertEquals(OK, service.getStatus());
    }

    @Test
    void getStatusWhenReadyMalfunction() {
        setIsReady(true);
        setIsMalfunction(true);

        assertEquals(MALFUNCTION, service.getStatus());
    }
}