package ru.tinkoff.academy.handyman.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.academy.handyman.BuildInfo;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class SystemServiceTest {
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final ManagedChannel managedChannel = mock(ManagedChannel.class);
    private final SystemService service = new SystemService(buildProperties, managedChannel);

    @Test
    void getStatus() {
        setField(service, "isGrpcStatus", false);

        assertEquals("OK", service.getStatus());
    }

    @Test
    void getStatusGRPC() {
        setField(service, "isGrpcStatus", true);
        var connectivityState = ConnectivityState.READY;

        when(managedChannel.getState(anyBoolean())).thenReturn(connectivityState);

        assertEquals("READY", service.getStatus());

        verify(managedChannel).getState(true);
    }

    @Test
    void getReadiness() {
        setField(service, "isGrpcStatus", false);

        when(buildProperties.getName()).thenReturn("name");

        assertEquals(Map.of("name", "OK"), service.getReadiness());

        verify(buildProperties).getName();
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
}