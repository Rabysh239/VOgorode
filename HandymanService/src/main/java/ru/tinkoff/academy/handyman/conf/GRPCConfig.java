package ru.tinkoff.academy.handyman.conf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GRPCConfig {
    private final GRPCProperties gRPCProperties;

    /**
     * Creates <b>managedChannel</b> bean with grpc properties and plaintext.
     *
     * @return <b>managedChannel</b>
     */
    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(gRPCProperties.getAddress(), gRPCProperties.getPort())
                .usePlaintext()
                .build();
    }
}
