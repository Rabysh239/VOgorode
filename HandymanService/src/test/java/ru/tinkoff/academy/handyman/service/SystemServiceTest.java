package ru.tinkoff.academy.handyman.service;

import io.grpc.ManagedChannel;
import org.junit.jupiter.api.Test;

import static io.grpc.ConnectivityState.READY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.tinkoff.academy.handyman.ReadinessStatus.NOK;
import static ru.tinkoff.academy.handyman.ReadinessStatus.OK;

class SystemServiceTest {
    private final ManagedChannel managedChannel = mock(ManagedChannel.class);
    private final SystemService service = new SystemService(managedChannel);

    @Test
    void getStatusWhenNotReady() {
        assertEquals(NOK, service.getStatus());
    }

    @Test
    void getStatusWhenReady() {
        SystemService.doReady();

        assertEquals(OK, service.getStatus());
    }

    @Test
    void getStatusGrpc() {
        when(managedChannel.getState(anyBoolean())).thenReturn(READY);

        assertEquals(READY, service.getGrpcStatus());

        verify(managedChannel).getState(true);
    }
}