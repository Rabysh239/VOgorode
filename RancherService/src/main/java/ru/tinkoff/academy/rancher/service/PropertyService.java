package ru.tinkoff.academy.rancher.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Value("${status.grpc.enabled}")
    private Boolean isGrpcStatus;

    /**
     * @return {@link PropertyService#isGrpcStatus}
     */
    public boolean getIsGrpcStatus() {
        return isGrpcStatus;
    }
}
