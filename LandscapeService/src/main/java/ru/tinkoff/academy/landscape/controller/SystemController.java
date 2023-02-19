package ru.tinkoff.academy.landscape.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.landscape.service.SystemService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemController {
    private final SystemService service;

    /**
     * Just complete
     */
    @GetMapping("/liveness")
    public void liveness() {
    }

    /**
     * Check readiness
     *
     * @return readiness
     * @see SystemService#readiness()
     */
    @GetMapping("/readiness")
    public Map<String, String> readiness() {
        return service.readiness();
    }
}

