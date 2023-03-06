package ru.tinkoff.academy.rancher.service;

import io.grpc.ManagedChannel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.academy.rancher.conf.GRPCProperties;
import ru.tinkoff.academy.rancher.data.BuildInfo;

import static io.grpc.ConnectivityState.READY;
import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SystemServiceTest {
    private final GRPCProperties gRPCProperties = mock(GRPCProperties.class);
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final ManagedChannel managedChannel = mock(ManagedChannel.class);
    private final SystemService service = new SystemService(gRPCProperties, buildProperties, managedChannel);

    @Test
    void getReadinessStatusWhenReady() {
        when(gRPCProperties.getStatusEnabled()).thenReturn(false);
        SystemService.setIsReady(true);

        assertEquals("OK", service.getReadinessStatus());

        verify(gRPCProperties).getStatusEnabled();
    }

    @Test
    void getReadinessStatusWhenNotReady() {
        when(gRPCProperties.getStatusEnabled()).thenReturn(false);
        SystemService.setIsReady(false);

        assertEquals("NOK", service.getReadinessStatus());

        verify(gRPCProperties).getStatusEnabled();
    }

    @Test
    void getReadinessStatusWhenGrpc() {
        when(gRPCProperties.getStatusEnabled()).thenReturn(true);
        when(managedChannel.getState(anyBoolean())).thenReturn(READY);

        assertEquals("READY", service.getReadinessStatus());

        verify(gRPCProperties).getStatusEnabled();
        verify(managedChannel).getState(true);
    }

    @Test
    void getReadiness() {
        when(buildProperties.getName()).thenReturn("name");
        when(gRPCProperties.getStatusEnabled()).thenReturn(false);
        SystemService.setIsReady(true);

        assertEquals(entry("name", "OK"), service.getReadiness());

        verify(buildProperties).getName();
        verify(gRPCProperties).getStatusEnabled();
    }

    @Test
    void getBuildInfo() {
        when(buildProperties.getArtifact()).thenReturn("artifact");
        when(buildProperties.getName()).thenReturn("name");
        when(buildProperties.getGroup()).thenReturn("group");
        when(buildProperties.getVersion()).thenReturn("version");

        assertEquals(
                BuildInfo.builder()
                        .artifact("artifact")
                        .name("name")
                        .group("group")
                        .version("version")
                        .build(),
                service.getBuildInfo()
        );

        verify(buildProperties).getArtifact();
        verify(buildProperties).getName();
        verify(buildProperties).getGroup();
        verify(buildProperties).getVersion();
    }

    @Test
    void getStatusWhenReadyMalfunction() {
        setIsReady(true);
        setIsMalfunction(true);

        assertEquals(MALFUNCTION, service.getStatus());
    }
}