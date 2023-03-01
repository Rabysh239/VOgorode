package ru.tinkoff.academy.landscape.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.academy.landscape.service.SystemService;

@Component
@RequiredArgsConstructor
public class ApplicationReadyEventListener {

    /**
     * When ApplicationReadyEvent changes readiness.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void changeReadinessStatus() {
        SystemService.doReady();
    }
}