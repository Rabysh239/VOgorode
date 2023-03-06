package ru.tinkoff.academy.handyman.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.handyman.service.SystemService;

import java.util.Map.Entry;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.handyman.data.ReadinessStatus.NOK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemController {
    private final SystemService service;

    /**
     * @return 200
     */
    @GetMapping("/liveness")
    public ResponseEntity<?> getLiveness() {
        return ok().build();
    }

    /**
     * @return if <em>status</em> != NOK serviceName : <em>status</em> else 503
     */
    @GetMapping("/readiness")
    public ResponseEntity<Entry<String, String>> getReadiness() {
        Entry<String, String> readiness = service.getReadiness();
        if (NOK.toString().equals(readiness.getValue())) {
            return status(SERVICE_UNAVAILABLE).build();
        }
        return ok(readiness);
    }
}

