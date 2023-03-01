package ru.tinkoff.academy.landscape.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.ReadinessStatus;

import static ru.tinkoff.academy.landscape.ReadinessStatus.*;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReady = false;
    private static volatile boolean isMalfunction = false;

    /**
     * @return if {@link SystemService#isReady} == false {@link ReadinessStatus#NOK} else if {@link SystemService#isMalfunction} == true {@link ReadinessStatus#MALFUNCTION} else {@link ReadinessStatus#OK}.
     */
    public ReadinessStatus getStatus() {
        return isReady ? (isMalfunction ? MALFUNCTION : OK) : NOK;
    }

    /**
     * Sets {@link SystemService#isReady} to given <em>value</em>.
     *
     * @param isReady is <em>value</em> for setting
     */
    public static void setIsReady(boolean isReady) {
        SystemService.isReady = isReady;
    }

    /**
     * Sets {@link SystemService#isMalfunction} to given <em>value</em>.
     *
     * @param isMalfunction is <em>value</em> for setting
     */
    public static void setIsMalfunction(boolean isMalfunction) {
        SystemService.isMalfunction = isMalfunction;
    }
}