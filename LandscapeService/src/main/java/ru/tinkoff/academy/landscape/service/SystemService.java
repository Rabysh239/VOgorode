package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReadiness = false;

    /**
     * @return {@link SystemService#isReadiness}
     */
    public boolean getReadiness() {
        return isReadiness;
    }

    /**
     * Changes {@link SystemService#isReadiness} to true.
     */
    public static void doReady() {
        isReadiness = true;
    }
}