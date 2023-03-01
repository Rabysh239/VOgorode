package ru.tinkoff.academy.landscape.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.academy.landscape.ServiceStatus;
import ru.tinkoff.academy.landscape.service.ServiceStatusService;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceStatusController {
    private final ServiceStatusService serviceStatusService;

    /**
     * Returns map of entries: serviceName to statuses
     *
     * @see ServiceStatusService#getStatuses()
     */
    @GetMapping("/statuses")
    public ResponseEntity<Map<String, List<ServiceStatus>>> getStatuses() {
        return ok(serviceStatusService.getStatuses());
    }
}
