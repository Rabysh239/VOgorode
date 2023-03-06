package ru.tinkoff.academy.rancher.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.tinkoff.academy.rancher.data.BuildInfo;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

@GrpcService
@RequiredArgsConstructor
public class StatusServiceImpl extends StatusServiceGrpc.StatusServiceImplBase {
    private final SystemService systemService;

    /**
     * Fills ReadinessResponse.
     *
     * @param responseObserver receives response.
     */
    @Override
    public void getReadiness(Empty request, StreamObserver<ReadinessResponse> responseObserver) {
        String status = systemService.getReadinessStatus();
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
        BuildInfo buildInfo = systemService.getBuildInfo();
        VersionResponse versionResponse = VersionResponse.newBuilder()
                .setArtifact(buildInfo.getArtifact())
                .setName(buildInfo.getName())
                .setGroup(buildInfo.getGroup())
                .setVersion(buildInfo.getVersion())
                .build();
        responseObserver.onNext(versionResponse);
        responseObserver.onCompleted();
    }
}
