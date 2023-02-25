package ru.tinkoff.academy.handyman;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class Config {
    private final Environment env;

    /**
     * Creates <b>managedChannel</b> bean with grpc properties and plaintext.
     *
     * @return <b>managedChannel</b>
     */
    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(
                        env.getProperty("grpc.server.address"),
                        Integer.parseInt(Objects.requireNonNull(env.getProperty("grpc.server.port")))
                )
                .usePlaintext()
                .build();
    }
}
