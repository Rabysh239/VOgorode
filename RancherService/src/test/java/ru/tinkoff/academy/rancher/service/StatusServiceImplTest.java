package ru.tinkoff.academy.rancher.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.VersionResponse;

import static io.grpc.ConnectivityState.READY;
import static org.mockito.Mockito.*;
import static ru.tinkoff.academy.rancher.ReadinessStatus.OK;

class StatusServiceImplTest {
    private final SystemService systemService = mock(SystemService.class);
    private final PropertyService propertyService = mock(PropertyService.class);
    private final BuildProperties buildProperties = mock(BuildProperties.class);
    private final Empty empty = mock(Empty.class);
    private final StatusServiceImpl service = new StatusServiceImpl(systemService, propertyService, buildProperties);

    @Test
    void getReadiness() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<ReadinessResponse>) mock(StreamObserver.class);
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder().setStatus("OK").build();

        when(propertyService.getIsGrpcStatus()).thenReturn(false);
        when(systemService.getStatus()).thenReturn(OK);

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

        when(propertyService.getIsGrpcStatus()).thenReturn(true);
        when(systemService.getGrpcStatus()).thenReturn(READY);

        service.getReadiness(empty, responseObserver);

        verify(responseObserver).onNext(readinessResponse);
        verify(responseObserver).onCompleted();
        verifyNoInteractions(empty);
    }

    @Test
    void getVersion() {
        @SuppressWarnings("unchecked")
        var responseObserver = (StreamObserver<VersionResponse>) mock(StreamObserver.class);
        VersionResponse versionResponse = VersionResponse.newBuilder()
                .setArtifact("artifact")
                .setName("name")
                .setGroup("group")
                .setVersion("version")
                .build();

        when(buildProperties.getArtifact()).thenReturn("artifact");
        when(buildProperties.getName()).thenReturn("name");
        when(buildProperties.getGroup()).thenReturn("group");
        when(buildProperties.getVersion()).thenReturn("version");

        service.getVersion(empty, responseObserver);

        verify(buildProperties).getArtifact();
        verify(buildProperties).getName();
        verify(buildProperties).getGroup();
        verify(buildProperties).getVersion();
        verify(responseObserver).onNext(versionResponse);
        verify(responseObserver).onCompleted();
        verifyNoInteractions(empty);
    }
}