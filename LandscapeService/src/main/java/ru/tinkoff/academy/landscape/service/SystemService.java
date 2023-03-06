package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.data.ReadinessStatus;

import java.util.Map.Entry;

import static java.util.Map.entry;
import static ru.tinkoff.academy.landscape.data.ReadinessStatus.NOK;
import static ru.tinkoff.academy.landscape.data.ReadinessStatus.OK;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final BuildProperties buildProperties;
    private static volatile boolean isReady = false;
    private static volatile boolean isMalfunction = false;

    /**
     * @return if {@link SystemService#isReady} == true return OK else NOK
     */
    public ReadinessStatus getReadinessStatus() {
        return isReady ? OK : NOK;
    }

    /**
     * @return entry of serviceName : readiness status
     * @see SystemService#getReadinessStatus()
     */
    public Entry<String, String> getReadiness() {
        return entry(buildProperties.getName(), getReadinessStatus().toString());
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