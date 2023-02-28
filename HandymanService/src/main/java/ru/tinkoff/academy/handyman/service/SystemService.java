package ru.tinkoff.academy.handyman.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.handyman.ReadinessStatus;

import static ru.tinkoff.academy.handyman.ReadinessStatus.NOK;
import static ru.tinkoff.academy.handyman.ReadinessStatus.OK;

@Service
@RequiredArgsConstructor
public class SystemService {
    private static volatile boolean isReady = false;
    private final ManagedChannel managedChannel;

    /**
     * @return if {@link SystemService#isReady} == true "OK" else "NOK".
     */
    public ReadinessStatus getStatus() {
        return isReady ? OK : NOK;
    }

    /**
     * @see ManagedChannel#getState(boolean)
     */
    public ConnectivityState getGrpcStatus() {
        return managedChannel.getState(true);
    }

    /**
     * Changes {@link SystemService#isReady} to true.
     */
    public static void doReady() {
        isReady = true;
    }
}