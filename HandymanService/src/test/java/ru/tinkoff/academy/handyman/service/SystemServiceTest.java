package ru.tinkoff.academy.handyman.service;

import io.grpc.ManagedChannel;
import org.junit.jupiter.api.Test;

import static io.grpc.ConnectivityState.READY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.tinkoff.academy.handyman.ReadinessStatus.*;
import static ru.tinkoff.academy.handyman.service.SystemService.setIsReady;
import static ru.tinkoff.academy.handyman.service.SystemService.setIsMalfunction;

class SystemServiceTest {
    private final ManagedChannel managedChannel = mock(ManagedChannel.class);
    private final SystemService service = new SystemService(managedChannel);

    @Test
    void getStatusWhenNotReady() {
        setIsReady(false);
        setIsMalfunction(false);

        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenNotReadyMalfunction() {
        setIsReady(false);
        setIsMalfunction(true);

        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenReady() {
        setIsReady(true);
        setIsMalfunction(false);

        assertEquals(OK, service.getStatus());
    }

    @Test
    void getStatusWhenReadyMalfunction() {
        setIsReady(true);
        setIsMalfunction(true);

        assertEquals(MALFUNCTION, service.getStatus());
    }

    @Test
    void getStatusGrpc() {
        when(managedChannel.getState(anyBoolean())).thenReturn(READY);

        assertEquals(READY, service.getGrpcStatus());

        verify(managedChannel).getState(true);
    }
}