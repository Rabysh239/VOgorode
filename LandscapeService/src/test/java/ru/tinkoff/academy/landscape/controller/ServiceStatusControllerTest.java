package ru.tinkoff.academy.landscape.controller;

import org.junit.jupiter.api.Test;
import ru.tinkoff.academy.landscape.ServiceStatus;
import ru.tinkoff.academy.landscape.service.ServiceStatusService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceStatusControllerTest {
    private final ServiceStatusService service = mock(ServiceStatusService.class);
    private final ServiceStatusController controller = new ServiceStatusController(service);

    @Test
    void getStatuses() {
        when(service.getStatuses()).thenReturn(
                Map.of("RancherService",
                        List.of(ServiceStatus.builder()
                                .host("localhost:8091")
                                .status("OK")
                                .artifact("RancherService")
                                .group("ru.tinkoff.academy")
                                .version("0.1.1-SNAPSHOT")
                                .build()
                        ))
        );

        assertEquals(Map.of("RancherService",
                        List.of(ServiceStatus.builder()
                                .host("localhost:8091")
                                .status("OK")
                                .artifact("RancherService")
                                .group("ru.tinkoff.academy")
                                .version("0.1.1-SNAPSHOT")
                                .build())),
                controller.getStatuses());
    }
}