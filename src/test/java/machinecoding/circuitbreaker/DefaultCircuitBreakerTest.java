package machinecoding.circuitbreaker;

import machinecoding.circuitbreaker.CircuitBreaker;
import machinecoding.circuitbreaker.CircuitBreakerConfig;
import machinecoding.circuitbreaker.DefaultCircuitBreaker;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class DefaultCircuitBreakerTest {

    private CircuitBreakerConfig config;
    private CircuitBreaker circuitBreaker;

    @Before
    public void setUp() {
        // Setup CircuitBreakerConfig with a failureThreshold of 2, halfOpenRetryDelay of 1000ms, and timeout of 2000ms
        config = new CircuitBreakerConfig(2, 1000, 2000);
        circuitBreaker = new DefaultCircuitBreaker(config);
    }

    @Test
    public void testCircuitBreakerClosedState() {
        // Define operation that succeeds
        Supplier<String> successfulOperation = () -> "Success";
        Supplier<String> fallbackOperation = () -> "Fallback";

        // CircuitBreaker should return the successful operation result and remain in CLOSED state
        String result = circuitBreaker.execute(successfulOperation, fallbackOperation);
        assertEquals("Success", result);
        assertEquals(State.CLOSED, circuitBreaker.getState());
    }

    @Test
    public void testCircuitBreakerOpenState() {
        // Define operation that fails
        Supplier<String> failingOperation = () -> {
            throw new RuntimeException("Operation failed");
        };
        Supplier<String> fallbackOperation = () -> "Fallback";

        // Trigger failures to transition to OPEN state
        circuitBreaker.execute(failingOperation, fallbackOperation);
        circuitBreaker.execute(failingOperation, fallbackOperation);

        // The circuit breaker should now be in OPEN state and return fallback result
        assertEquals(State.OPEN, circuitBreaker.getState());
        String result = circuitBreaker.execute(failingOperation, fallbackOperation);
        assertEquals("Fallback", result);
    }

    @Test
    public void testCircuitBreakerHalfOpenToClosedTransition() throws InterruptedException {
        // Define operation that fails initially
        Supplier<String> failingOperation = () -> {
            throw new RuntimeException("Operation failed");
        };
        Supplier<String> fallbackOperation = () -> "Fallback";

        // Trigger failures to transition to OPEN state
        circuitBreaker.execute(failingOperation, fallbackOperation);
        circuitBreaker.execute(failingOperation, fallbackOperation);

        // Wait for timeout to transition to HALF_OPEN state
        Thread.sleep(2100);  // Slightly longer than timeout to ensure state transition

        // CircuitBreaker should be in HALF_OPEN state
        assertEquals(State.HALF_OPEN, circuitBreaker.getState());

        // Now perform a successful operation to transition to CLOSED state
        Supplier<String> successfulOperation = () -> "Success";
        String result = circuitBreaker.execute(successfulOperation, fallbackOperation);
        assertEquals("Success", result);
        assertEquals(State.CLOSED, circuitBreaker.getState());
    }

    @Test
    public void testCircuitBreakerHalfOpenToOpenTransition() throws InterruptedException {
        // Define operation that fails initially
        Supplier<String> failingOperation = () -> {
            throw new RuntimeException("Operation failed");
        };
        Supplier<String> fallbackOperation = () -> "Fallback";

        // Trigger failures to transition to OPEN state
        circuitBreaker.execute(failingOperation, fallbackOperation);
        circuitBreaker.execute(failingOperation, fallbackOperation);

        // Wait for timeout to transition to HALF_OPEN state
        Thread.sleep(2100);  // Slightly longer than timeout to ensure state transition

        // CircuitBreaker should be in HALF_OPEN state
        assertEquals(State.HALF_OPEN, circuitBreaker.getState());

        // Now perform a failing operation to transition back to OPEN state
        String result = circuitBreaker.execute(failingOperation, fallbackOperation);
        assertEquals("Fallback", result);
        assertEquals(State.OPEN, circuitBreaker.getState());
    }

    @Test
    public void testCircuitBreakerReset() {
        // Define operation that fails
        Supplier<String> failingOperation = () -> {
            throw new RuntimeException("Operation failed");
        };
        Supplier<String> fallbackOperation = () -> "Fallback";

        // Trigger failures to transition to OPEN state
        circuitBreaker.execute(failingOperation, fallbackOperation);
        circuitBreaker.execute(failingOperation, fallbackOperation);

        // Reset the CircuitBreaker
        circuitBreaker.reset();

        // After reset, CircuitBreaker should be in CLOSED state
        assertEquals(State.CLOSED, circuitBreaker.getState());

        // Perform a successful operation
        Supplier<String> successfulOperation = () -> "Success";
        String result = circuitBreaker.execute(successfulOperation, fallbackOperation);
        assertEquals("Success", result);
    }
}