package ru.tinkoff.academy.handyman.conf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "grpc.server")
public class GRPCProperties {
    private final Boolean statusEnabled;
    private final String address;
    private final int port;
}
