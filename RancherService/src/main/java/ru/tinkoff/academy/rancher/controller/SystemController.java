package ru.tinkoff.academy.rancher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.rancher.ReadinessStatus;
import ru.tinkoff.academy.rancher.service.PropertyService;
import ru.tinkoff.academy.rancher.service.SystemService;

import java.util.Map;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.rancher.ReadinessStatus.NOK;
import static ru.tinkoff.academy.rancher.ReadinessStatus.OK;
import static ru.tinkoff.academy.rancher.service.SystemService.setIsMalfunction;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/system")
public class SystemController {
    private final SystemService service;
    private final PropertyService propertyService;
    private final BuildProperties buildProperties;

    /**
     * @return 200.
     */
    @GetMapping("/liveness")
    public ResponseEntity<?> getLiveness() {
        return ok().build();
    }

    /**
     * @return if isGrpcStatus 200 serviceName : grpcStatus else if readiness serviceName : status else 503.
     */
    @GetMapping("/readiness")
    public ResponseEntity<Map<String, String>> getReadiness() {
        if (propertyService.getIsGrpcStatus()) {
            return ok(Map.of(buildProperties.getName(), service.getGrpcStatus().name()));
        }
        ReadinessStatus readinessStatus = service.getStatus();
        return readinessStatus != NOK ? ok(Map.of(buildProperties.getName(), readinessStatus.toString())) : status(SERVICE_UNAVAILABLE).build();
    }

    /**
     * Switches malfunction status enabled parameter to give <em>value</em>.
     *
     * @param malfunctionEnabled is <em>value</em> for switching.
     */
    @PostMapping("/forceMalfunction")
    public ResponseEntity<?> forceMalfunction(@RequestParam(defaultValue = "true") boolean malfunctionEnabled) {
        setIsMalfunction(malfunctionEnabled);
        log.info(String.format("Malfunction status enabled parameter switch to %s", malfunctionEnabled));
        return ok().build();
    }
}

