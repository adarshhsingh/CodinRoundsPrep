package machinecoding.circuitbreaker;

import java.util.function.Supplier;

public class CircuitBreakerConfig {
    private int failureThreshold;
    private long halfOpenRetryDelay;
    private long timeout;

    public CircuitBreakerConfig(int failureThreshold, long halfOpenRetryDelay, long timeout) {
        this.failureThreshold = failureThreshold;
        this.halfOpenRetryDelay = halfOpenRetryDelay;
        this.timeout = timeout;
    }

    // Getters
    public int getFailureThreshold() {
        return failureThreshold;
    }

    public long getTimeout() {
        return timeout;
    }

    public long getHalfOpenRetryDelay() {
        return halfOpenRetryDelay;
    }
}
