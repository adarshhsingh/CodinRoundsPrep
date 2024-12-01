package machinecoding.circuitbreaker;

import java.util.function.Supplier;

public interface CircuitBreaker {
    <T> T execute(Supplier<T> operation, Supplier<T> fallback);
    void reset();
    State getState();
}
