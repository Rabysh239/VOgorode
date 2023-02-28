package ru.tinkoff.academy.handyman.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.academy.handyman.service.PropertyService;
import ru.tinkoff.academy.handyman.service.SystemService;

import java.util.Map;

import static io.grpc.ConnectivityState.READY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.handyman.ReadinessStatus.NOK;
import static ru.tinkoff.academy.handyman.ReadinessStatus.OK;

class SystemControllerTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final SystemService service = mock(SystemService.class);
    private final PropertyService propertyService = mock(PropertyService.class);
    private final SystemController controller = new SystemController(service, propertyService, buildProperties);

    @Test
    void getLiveness() {
        assertEquals(HttpStatus.OK, controller.getLiveness().getStatusCode());
    }

    @Test
    void getReadinessWhenReady() {
        when(propertyService.getIsGrpcStatus()).thenReturn(false);
        when(service.getStatus()).thenReturn(OK);
        when(buildProperties.getName()).thenReturn("name");

        assertEquals(ResponseEntity.ok(Map.of("name", "OK")), controller.getReadiness());

        verify(propertyService).getIsGrpcStatus();
        verify(buildProperties).getName();
        verify(service).getStatus();
    }

    @Test
    void getReadinessWhenNotReady() {
        when(propertyService.getIsGrpcStatus()).thenReturn(false);
        when(service.getStatus()).thenReturn(NOK);

        assertEquals(status(SERVICE_UNAVAILABLE).build(), controller.getReadiness());

        verify(propertyService).getIsGrpcStatus();
    }

    @Test
    void getReadinessIsGrpc() {
        when(propertyService.getIsGrpcStatus()).thenReturn(true);
        when(buildProperties.getName()).thenReturn("name");
        when(service.getGrpcStatus()).thenReturn(READY);

        assertEquals(ResponseEntity.ok(Map.of("name", "READY")), controller.getReadiness());

        verify(propertyService).getIsGrpcStatus();
        verify(buildProperties).getName();
        verify(service).getGrpcStatus();
    }
}