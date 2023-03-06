package ru.tinkoff.academy.handyman.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.handyman.service.SystemService;

import java.util.Map.Entry;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static ru.tinkoff.academy.handyman.data.ReadinessStatus.NOK;
import static ru.tinkoff.academy.handyman.service.SystemService.setIsMalfunction;

@RestController
@RequiredArgsConstructor
@Log4j2
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

