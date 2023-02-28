package ru.tinkoff.academy.handyman.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class PropertyServiceTest {
    private final PropertyService service = new PropertyService();

    @Test
    void getIsGrpcStatusTrue() {
        setField(service, "isGrpcStatus", true);

        assertTrue(service.getIsGrpcStatus());
    }

    @Test
    void getIsGrpcStatusFalse() {
        setField(service, "isGrpcStatus", false);

        assertFalse(service.getIsGrpcStatus());
    }
}