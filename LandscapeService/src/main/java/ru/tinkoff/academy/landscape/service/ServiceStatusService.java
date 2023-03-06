package ru.tinkoff.academy.landscape.service;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.data.ServiceStatus;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.protobuf.Empty.getDefaultInstance;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;
import static ru.tinkoff.proto.StatusServiceGrpc.newBlockingStub;

@Service
@Setter
@RequiredArgsConstructor
public class ServiceStatusService {
    private final GrpcChannelsProperties grpcChannelsProperties;
    private final GrpcChannelFactory grpcChannelFactory;

    @Value("${service.listOfNames}")
    private String[] serviceNames;

    /**
     * @return map of entries: serviceName to list of {@link ServiceStatus} of different instances
     */
    public Map<String, List<ServiceStatus>> getStatuses() {
        return grpcChannelsProperties.getClient().keySet().stream()
                .filter(x -> nonNull(serviceNameMapper(x)))
                .collect(
                        toMap(
                                this::serviceNameMapper,
                                x -> {
                                    ServiceStatus status = getStatus(x);
                                    return new ArrayList<>(nonNull(status) ? List.of(status) : List.of());
                                },
                                (x, y) -> {
                                    x.addAll(y);
                                    return x;
                                }
                        )
                );
    }

    private String serviceNameMapper(String rawName) {
        return stream(serviceNames).filter(rawName::startsWith).findFirst().orElse(null);
    }

    private ServiceStatus getStatus(String serviceName) {
        Channel serviceChannel = grpcChannelFactory.createChannel(serviceName);
        StatusServiceGrpc.StatusServiceBlockingStub statusServiceBlockingStub = newBlockingStub(serviceChannel);
        ReadinessResponse readiness;
        VersionResponse version;
        try {
            readiness = statusServiceBlockingStub.getReadiness(getDefaultInstance());
            version = statusServiceBlockingStub.getVersion(getDefaultInstance());
        } catch (StatusRuntimeException e) {
            return null;
        }
        return ServiceStatus.builder()
                .host(statusServiceBlockingStub.getChannel().authority())
                .status(readiness.getStatus())
                .artifact(version.getArtifact())
                .name(version.getName())
                .group(version.getGroup())
                .version(version.getVersion())
                .build();
    }
}
