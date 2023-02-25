package ru.tinkoff.academy.handyman.service;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.BuildInfo;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReadiness = false;
    private final BuildProperties buildProperties;
    private final ManagedChannel managedChannel;
    @Value("${status.grpc.enabled}")
    private Boolean isGrpcStatus;

    /**
     * @return if isGrpcStatus == true returns statusGRPC else "OK"
     * @see ManagedChannel#getState(boolean)
     */
    public String getStatus() {
        return isGrpcStatus ? getStatusGrpc() : "OK";
    }

    private String getStatusGrpc() {
        return managedChannel.getState(true).name();
    }

    /**
     * @return {@link SystemService#isReadiness}
     */
    public boolean getReadiness() {
        return isReadiness;
    }

    /**
     * Changes {@link SystemService#isReadiness} to true.
     */
    public static void doReady() {
        isReadiness = true;
    }

    /**
     * Fills build info from {@link BuildProperties}
     *
     * @return filed {@link BuildInfo}
     */
    public BuildInfo getBuildInfo() {
        return BuildInfo.builder()
                .artifact(buildProperties.getArtifact())
                .name(buildProperties.getName())
                .group(buildProperties.getGroup())
                .version(buildProperties.getVersion())
                .build();
    }
}