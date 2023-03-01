package ru.tinkoff.academy.rancher.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.rancher.ReadinessStatus;

import static ru.tinkoff.academy.rancher.ReadinessStatus.*;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReady = false;
    private static volatile boolean isMalfunction = false;
    private final ManagedChannel managedChannel;

    /**
     * @return if {@link SystemService#isReady} == false {@link ReadinessStatus#NOK} else if {@link SystemService#isMalfunction} == true {@link ReadinessStatus#MALFUNCTION} else {@link ReadinessStatus#OK}.
     */
    public ReadinessStatus getStatus() {
        return isReady ? (isMalfunction ? MALFUNCTION : OK) : NOK;
    }

    /**
     * @see ManagedChannel#getState(boolean)
     */
    public ConnectivityState getGrpcStatus() {
        return managedChannel.getState(true);
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