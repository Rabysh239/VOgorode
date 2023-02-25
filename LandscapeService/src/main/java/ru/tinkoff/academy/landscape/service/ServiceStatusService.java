package ru.tinkoff.academy.landscape.service;

import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;
import net.devh.boot.grpc.client.config.GrpcChannelsProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.ServiceStatus;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

import java.util.*;

import static com.google.protobuf.Empty.getDefaultInstance;
import static java.util.Arrays.stream;
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
        Map<String, List<ServiceStatus>> nameToStatutes = new HashMap<>();
        for (String rawName : grpcChannelsProperties.getClient().keySet()) {
            String serviceName = serviceNameMapper(rawName);
            if (serviceName == null) continue;
            nameToStatutes.putIfAbsent(serviceName, new ArrayList<>());
            nameToStatutes.get(serviceName).add(getStatus(rawName));
        }
        return nameToStatutes;
    }

    private String serviceNameMapper(String rawName) {
        return stream(serviceNames).filter(rawName::startsWith).findFirst().orElse(null);
    }

    private ServiceStatus getStatus(String serviceName) {
        Channel serviceChannel = grpcChannelFactory.createChannel(serviceName);
        StatusServiceGrpc.StatusServiceBlockingStub statusServiceBlockingStub = newBlockingStub(serviceChannel);
        ReadinessResponse readiness = statusServiceBlockingStub.getReadiness(getDefaultInstance());
        VersionResponse version = statusServiceBlockingStub.getVersion(getDefaultInstance());
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
