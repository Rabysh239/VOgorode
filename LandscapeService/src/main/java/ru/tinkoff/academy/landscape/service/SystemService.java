package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final BuildProperties buildProperties;

    /**
     * @return map of entry = serviceName : "OK"
     */
    public Map<String, String> readiness() {
        return Map.of(buildProperties.getName(), "OK");
    }
}