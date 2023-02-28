package ru.tinkoff.academy.handyman.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.handyman.ReadinessStatus;
import ru.tinkoff.academy.handyman.service.PropertyService;
import ru.tinkoff.academy.handyman.service.SystemService;

import java.util.Map;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.handyman.ReadinessStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemController {
    private final SystemService service;
    private final PropertyService propertyService;
    private final BuildProperties buildProperties;

    /**
     * @return 200
     */
    @GetMapping("/liveness")
    public ResponseEntity<?> getLiveness() {
        return ok().build();
    }

    /**
     * @return if isGrpcStatus 200 serviceName : grpcStatus else if readiness serviceName : status else 503
     */
    @GetMapping("/readiness")
    public ResponseEntity<Map<String, String>> getReadiness() {
        if (propertyService.getIsGrpcStatus()) {
            return ok(Map.of(buildProperties.getName(), service.getGrpcStatus().name()));
        }
        ReadinessStatus readinessStatus = service.getStatus();
        return readinessStatus == OK ? ok(Map.of(buildProperties.getName(), readinessStatus.toString())) : status(SERVICE_UNAVAILABLE).build();
    }
}

