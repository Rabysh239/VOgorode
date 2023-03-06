package ru.tinkoff.academy.landscape.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.academy.landscape.data.ReadinessStatus;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SystemServiceTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final SystemService service = new SystemService(buildProperties);

    @Test
    void getReadinessStatusWhenReady() {
        SystemService.setIsReady(true);

        assertEquals(ReadinessStatus.OK, service.getReadinessStatus());
    }

    @Test
    void getReadinessStatusWhenNotReady() {
        SystemService.setIsReady(false);

        assertEquals(ReadinessStatus.NOK, service.getReadinessStatus());
    }

    @Test
    void getReadiness() {
        when(buildProperties.getName()).thenReturn("name");
        SystemService.setIsReady(true);

        assertEquals(entry("name", "OK"), service.getReadiness());

        verify(buildProperties).getName();
    }
}