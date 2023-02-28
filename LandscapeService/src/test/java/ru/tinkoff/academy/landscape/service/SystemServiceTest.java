package ru.tinkoff.academy.landscape.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.tinkoff.academy.landscape.ReadinessStatus.NOK;
import static ru.tinkoff.academy.landscape.ReadinessStatus.OK;

class SystemServiceTest {
    private final SystemService service = new SystemService();

    @Test
    void getStatusWhenNotReady() {
        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenReady() {
        SystemService.doReady();

        assertEquals(OK, service.getStatus());
    }
}