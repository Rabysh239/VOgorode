package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.ReadinessStatus;

import static ru.tinkoff.academy.landscape.ReadinessStatus.NOK;
import static ru.tinkoff.academy.landscape.ReadinessStatus.OK;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReady = false;

    /**
     * @return "OK"
     */
    public ReadinessStatus getStatus() {
        return isReady ? OK : NOK;
    }

    /**
     * Changes {@link SystemService#isReady} to true.
     */
    public static void doReady() {
        isReady = true;
    }
}