package ru.tinkoff.academy.rancher.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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