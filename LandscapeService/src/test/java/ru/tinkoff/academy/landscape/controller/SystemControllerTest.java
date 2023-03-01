package ru.tinkoff.academy.landscape.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.academy.landscape.ReadinessStatus;
import ru.tinkoff.academy.landscape.service.SystemService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.landscape.ReadinessStatus.NOK;
import static ru.tinkoff.academy.landscape.ReadinessStatus.OK;

class SystemControllerTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final SystemService service = mock(SystemService.class);
    private final SystemController controller = new SystemController(service, buildProperties);

    @Test
    void getLiveness() {
        assertEquals(HttpStatus.OK, controller.getLiveness().getStatusCode());
    }

    @Test
    void getReadinessWhenReady() {
        when(buildProperties.getName()).thenReturn("name");
        when(service.getStatus()).thenReturn(OK);

        assertEquals(ResponseEntity.ok(Map.of("name", "OK")), controller.getReadiness());

        verify(buildProperties).getName();
        verify(service).getStatus();
    }

    @Test
    void getReadinessWhenNotReady() {
        when(service.getStatus()).thenReturn(NOK);

        assertEquals(status(SERVICE_UNAVAILABLE).build(), controller.getReadiness());

        verify(service).getStatus();
    }

    @Test
    void forceMalfunction() {
        assertEquals(HttpStatus.OK, controller.forceMalfunction(true).getStatusCode());
    }
}