package ru.tinkoff.academy.landscape.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.academy.landscape.data.ReadinessStatus;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.tinkoff.academy.landscape.data.ReadinessStatus.MALFUNCTION;
import static ru.tinkoff.academy.landscape.service.SystemService.setIsMalfunction;
import static ru.tinkoff.academy.landscape.service.SystemService.setIsReady;

class SystemServiceTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final SystemService service = new SystemService(buildProperties);

    @Test
    void getReadinessStatusWhenReady() {
        setIsReady(true);
        setIsMalfunction(false);

        assertEquals(ReadinessStatus.OK, service.getReadinessStatus());
    }

    @Test
    void getReadinessStatusWhenNotReady() {
        setIsReady(false);

        assertEquals(ReadinessStatus.NOK, service.getReadinessStatus());
    }

    @Test
    void getReadiness() {
        when(buildProperties.getName()).thenReturn("name");
        setIsReady(true);
        setIsMalfunction(false);

        assertEquals(entry("name", "OK"), service.getReadiness());

        verify(buildProperties).getName();
    }

    @Test
    void getStatusWhenReadyMalfunction() {
        setIsReady(true);
        setIsMalfunction(true);

        assertEquals(MALFUNCTION, service.getReadinessStatus());
    }
}