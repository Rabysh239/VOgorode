package ru.tinkoff.academy.rancher.service;

import io.grpc.ManagedChannel;
import org.junit.jupiter.api.Test;

import static io.grpc.ConnectivityState.READY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.tinkoff.academy.rancher.ReadinessStatus.*;
import static ru.tinkoff.academy.rancher.service.SystemService.setIsMalfunction;
import static ru.tinkoff.academy.rancher.service.SystemService.setIsReady;

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