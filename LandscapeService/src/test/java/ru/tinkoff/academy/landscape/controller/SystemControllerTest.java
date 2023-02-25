package ru.tinkoff.academy.landscape.controller;

import org.junit.jupiter.api.Test;
import ru.tinkoff.academy.landscape.service.SystemService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SystemControllerTest {
    private final SystemService service = mock(SystemService.class);
    private final SystemController controller = new SystemController(service);

    @Test
    void getLiveness() {
        controller.getLiveness();
    }

    @Test
    void getReadiness() {
        when(service.getReadiness()).thenReturn(Map.of("name", "status"));

        assertEquals(Map.of("name", "status"), service.getReadiness());

        verify(service).getReadiness();
    }
}