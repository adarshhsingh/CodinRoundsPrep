package machinecoding.circuitbreaker;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class DefaultCircuitBreaker implements CircuitBreaker {
    private State state = State.CLOSED;
    private CircuitBreakerConfig circuitBreakerConfig;
    private AtomicInteger failureCount = new AtomicInteger(0);
    private AtomicLong lastFailureTime = new AtomicLong(0);

    public DefaultCircuitBreaker(CircuitBreakerConfig config) {
        this.circuitBreakerConfig = config;
    }

    private void transitionToClosedState(){
        recordTransition(State.CLOSED);
        state = State.CLOSED;
        failureCount.set(0);
        lastFailureTime.set(0);
    }
    private void transitionToOpenState(){
        recordTransition(State.OPEN);
        state = State.OPEN;
        lastFailureTime.set(System.currentTimeMillis());
    }

    private void transitionToHalfOpenState(){
        recordTransition(State.HALF_OPEN);
        state = State.HALF_OPEN;
    }

    private void recordFailure() {
        int failureCounts = failureCount.incrementAndGet();
        if( failureCounts >= circuitBreakerConfig.getFailureThreshold()) {
            transitionToOpenState();
        }
    }

    @Override
    public <T> T execute(Supplier<T> operation, Supplier<T> fallback) {
        evaluateState();
        if( state == State.OPEN) {
            return fallback.get();
        }

        if(state == State.HALF_OPEN) {
            if(System.currentTimeMillis() - lastFailureTime.get() < circuitBreakerConfig.getHalfOpenRetryDelay()) {
                return fallback.get();
            }
        }

        try {
             T result = operation.get();
             if(state != State.CLOSED) transitionToClosedState();
             return result;
        } catch (Exception e) {
             recordFailure();
             return fallback.get();
        }

    }

    private void evaluateState(){
        if(state == State.OPEN) {
            if( System.currentTimeMillis() - lastFailureTime.get() > circuitBreakerConfig.getTimeout() ) {
                transitionToHalfOpenState();
            }
        }
    }

    @Override
    public void reset() {
        transitionToClosedState();
    }


    @Override
    public State getState() {
        return state;
    }

    public void recordTransition(State toState) {
        System.out.println("Changing state from "+ state + " to " + toState);
    }
}
