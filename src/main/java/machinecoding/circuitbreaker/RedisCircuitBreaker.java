package machinecoding.circuitbreaker;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

public class RedisCircuitBreaker implements CircuitBreaker {
    private final Jedis jedis;
    private final String circuitBreakerKey;
    private final CircuitBreakerConfig circuitBreakerConfig;

    public RedisCircuitBreaker(Jedis jedis, String circuitBreakerKey, CircuitBreakerConfig circuitBreakerConfig) {
        this.jedis = jedis;
        this.circuitBreakerKey = circuitBreakerKey;
        this.circuitBreakerConfig = circuitBreakerConfig;

        // Initialize the circuit breaker state in Redis if not already set
        jedis.setnx((circuitBreakerKey + ":state"), State.CLOSED.name());
        jedis.setnx((circuitBreakerKey + ":failureCount"), "0");
    }

    private void transitionToClosedState(){
        log("Transistioning to  CLOSED  from "+ getState().name());
        jedis.set((circuitBreakerKey + ":state"), State.CLOSED.name());
        jedis.set((circuitBreakerKey + ":failureCount"), "0");

    }

    private void transitionToOpenState(){
        log("Transistioning to  OPEN  from "+ getState().name());
        jedis.set((circuitBreakerKey + ":state"), State.OPEN.name());
        jedis.set((circuitBreakerKey + "lastFailureTime"), String.valueOf(System.currentTimeMillis()));
    }

    private void transitionToHalfOpenState(){
        log("Transistioning to HALF OPEN STATE from "+ getState().name());
        jedis.set((circuitBreakerKey + ":state"), State.HALF_OPEN.name());
    }


    @Override
    public <T> T execute(Supplier<T> operation, Supplier<T> fallback) {
        State state = getState();

        if ( state == State.OPEN ) {
            return fallback.get();
        }

        if(state == State.HALF_OPEN) {
            if(System.currentTimeMillis() - getLastFailureTime() < circuitBreakerConfig.getHalfOpenRetryDelay()) {
                return fallback.get();
            }
        }

        try {
            T result = operation.get();
            transitionToClosedState();
            return result;
        } catch (Exception e) {
            handleFailure();
            return fallback.get();
        }
    }

    private void handleFailure() {
        long failureCount = Long.parseLong(jedis.get((circuitBreakerKey + ":failureCount")));
        failureCount++;
        log("Failure Count reached : "+ failureCount);
        if( failureCount >= circuitBreakerConfig.getFailureThreshold()) {
            log("Failure Count reached threshold : "+ failureCount);
            transitionToOpenState();
        }
    }


    @Override
    public void reset() {
        transitionToClosedState();
    }

    @Override
    public State getState() {
        State state = State.valueOf(jedis.get((circuitBreakerKey +":state")));
        return state;
    }

    public long getLastFailureTime() {
        String lastFailureTime = jedis.get((circuitBreakerKey +":lastFailureTime"));
        return (lastFailureTime != null) ? Long.parseLong(lastFailureTime):0;
    }

    private void log(String s) {
        System.out.println(s);
    }
}
