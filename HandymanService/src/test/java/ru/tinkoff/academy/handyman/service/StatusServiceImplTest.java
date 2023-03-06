package ru.tinkoff.academy.handyman.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import ru.tinkoff.academy.handyman.conf.GRPCProperties;
import ru.tinkoff.academy.handyman.data.BuildInfo;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.VersionResponse;

import static org.mockito.Mockito.*;

class StatusServiceImplTest {
    private final SystemService systemService = mock(SystemService.class);
    private final GRPCProperties gRPCProperties = mock(GRPCProperties.class);
    private final Empty empty = mock(Empty.class);
    private final StatusServiceImpl service = new StatusServiceImpl(systemService);

    @Test
    void getReadiness() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<ReadinessResponse>) mock(StreamObserver.class);
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder().setStatus("OK").build();

        when(gRPCProperties.getStatusEnabled()).thenReturn(false);
        when(systemService.getReadinessStatus()).thenReturn("OK");

        service.getReadiness(empty, responseObserver);

        verify(responseObserver).onNext(readinessResponse);
        verify(responseObserver).onCompleted();
        verifyNoInteractions(empty);
    }

    @Test
    void getReadinessGrpcStatus() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<ReadinessResponse>) mock(StreamObserver.class);
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder().setStatus("READY").build();

        when(gRPCProperties.getStatusEnabled()).thenReturn(true);
        when(systemService.getReadinessStatus()).thenReturn("READY");

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