package ru.tinkoff.academy.landscape.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class SystemServiceTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final SystemService service = new SystemService(buildProperties);

    @Test
    void getStatus() {
        assertEquals("OK", service.getStatus());
    }

    @Test
    void getReadiness() {
        when(buildProperties.getName()).thenReturn("name");

        assertEquals(Map.of("name", "OK"), service.getReadiness());

        verify(buildProperties).getName();
    }
}