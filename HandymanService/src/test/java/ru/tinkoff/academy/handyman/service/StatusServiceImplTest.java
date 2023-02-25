package ru.tinkoff.academy.handyman.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.tinkoff.academy.handyman.BuildInfo;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.VersionResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusServiceImplTest {
    private final SystemService systemService = mock(SystemService.class);
    private final Empty empty = mock(Empty.class);
    private final StatusServiceImpl service = new StatusServiceImpl(systemService);

    @Test
    void getReadiness() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<ReadinessResponse>) mock(StreamObserver.class);
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder().setStatus("status").build();

        when(systemService.getStatus()).thenReturn("status");

        service.getReadiness(empty, responseObserver);

        verify(responseObserver).onNext(readinessResponse);
        verify(responseObserver).onCompleted();
        verifyNoInteractions(empty);
    }

    @Test
    void getVersion() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<VersionResponse>) mock(StreamObserver.class);
        var buildInfo = mock(BuildInfo.class);
        VersionResponse versionResponse = VersionResponse.newBuilder()
                .setArtifact("artifact")
                .setName("name")
                .setGroup("group")
                .setVersion("version")
                .build();

        when(systemService.getBuildInfo()).thenReturn(buildInfo);
        when(buildInfo.getArtifact()).thenReturn("artifact");
        when(buildInfo.getName()).thenReturn("name");
        when(buildInfo.getGroup()).thenReturn("group");
        when(buildInfo.getVersion()).thenReturn("version");

        service.getVersion(empty, responseObserver);

        verify(buildInfo).getArtifact();
        verify(buildInfo).getName();
        verify(buildInfo).getGroup();
        verify(buildInfo).getVersion();
        verify(responseObserver).onNext(versionResponse);
        verify(responseObserver).onCompleted();
        verifyNoInteractions(empty);
    }
}