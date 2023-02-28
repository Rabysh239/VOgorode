package ru.tinkoff.academy.handyman.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

@GrpcService
@RequiredArgsConstructor
public class StatusServiceImpl extends StatusServiceGrpc.StatusServiceImplBase {
    private final SystemService systemService;
    private final PropertyService propertyService;
    private final BuildProperties buildProperties;

    /**
     * Fills ReadinessResponse.
     *
     * @param responseObserver receives response.
     */
    @Override
    public void getReadiness(Empty request, StreamObserver<ReadinessResponse> responseObserver) {
        String status = propertyService.getIsGrpcStatus() ? systemService.getGrpcStatus().name() : systemService.getStatus().toString();
        ReadinessResponse readinessResponse = ReadinessResponse.newBuilder()
                .setStatus(status)
                .build();
        responseObserver.onNext(readinessResponse);
        responseObserver.onCompleted();
    }

    /**
     * Fills VersionResponse.
     *
     * @param responseObserver receives response.
     */
    @Override
    public void getVersion(Empty request, StreamObserver<VersionResponse> responseObserver) {
        VersionResponse versionResponse = VersionResponse.newBuilder()
                .setArtifact(buildProperties.getArtifact())
                .setName(buildProperties.getName())
                .setGroup(buildProperties.getGroup())
                .setVersion(buildProperties.getVersion())
                .build();
        responseObserver.onNext(versionResponse);
        responseObserver.onCompleted();
    }
}
