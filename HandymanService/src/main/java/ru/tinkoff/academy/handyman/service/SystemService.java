package ru.tinkoff.academy.handyman.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.conf.GRPCProperties;
import ru.tinkoff.academy.handyman.data.BuildInfo;
import ru.tinkoff.academy.handyman.data.ReadinessStatus;

import java.util.Map.Entry;

import static java.util.Map.entry;
import static ru.tinkoff.academy.handyman.data.ReadinessStatus.*;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final GRPCProperties gRPCProperties;
    private final BuildProperties buildProperties;
    private final ManagedChannel managedChannel;
    private static volatile boolean isReady = false;
    private static volatile boolean isMalfunction = false;

    /**
     * @return if gRPC status enabled return gRPC status else return status
     */
    public String getReadinessStatus() {
        return gRPCProperties.getStatusEnabled() ? getGrpcStatus().toString() : getStatus().toString();
    }

    /**
     * @return entry of serviceName : readiness status
     * @see SystemService#getReadinessStatus()
     */
    public Entry<String, String> getReadiness() {
        return entry(buildProperties.getName(), getReadinessStatus());
    }

    /**
     * Sets {@link SystemService#isReady} to given <em>value</em>.
     *
     * @param isReady is <em>value</em> for setting
     */
    public static void setIsReady(boolean isReady) {
        SystemService.isReady = isReady;
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

    /**
     * Sets {@link SystemService#isMalfunction} to given <em>value</em>.
     *
     * @param isMalfunction is <em>value</em> for setting
     */
    public static void setIsMalfunction(boolean isMalfunction) {
        SystemService.isMalfunction = isMalfunction;
    }

    private ReadinessStatus getStatus() {
        return isReady ? (isMalfunction ? MALFUNCTION : OK) : NOK;
    }

    private ConnectivityState getGrpcStatus() {
        return managedChannel.getState(true);
    }
}