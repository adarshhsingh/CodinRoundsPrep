package machinecoding.sampleservice;

import machinecoding.circuitbreaker.CircuitBreaker;
import machinecoding.circuitbreaker.CircuitBreakerConfig;
import machinecoding.circuitbreaker.DefaultCircuitBreaker;
import machinecoding.circuitbreaker.RedisCircuitBreaker;
import redis.clients.jedis.Jedis;

import java.util.function.Supplier;

public class MyAPIService {
    private CircuitBreaker circuitBreaker;

    public MyAPIService(Jedis jedis) {
        // Configure the circuit breaker
        CircuitBreakerConfig config = new CircuitBreakerConfig(3, 5000, 1000);
        this.circuitBreaker = new RedisCircuitBreaker(jedis, "myAPICircuitBreaker", config);
    }

    // API method where the circuit breaker is applied
    public String performOperation() {
        // Fallback function in case the circuit is open or operation fails
        Supplier<String> fallback = () -> "Fallback response";

        // Main operation that might fail
        Supplier<String> operation = this::simulateHttpRequest;

        // Execute the operation through the circuit breaker
        return circuitBreaker.execute(operation, fallback);
    }

    private String simulateHttpRequest() {
        // Simulate a potentially failing operation, like an HTTP request
        double randomNum = Math.random();
        System.out.println("EXECUTING REQUEST: simulateHttpRequest");
        System.out.println(randomNum);
        if (randomNum > 0.5) {
            throw new RuntimeException("Simulated failure");
        }
        return "Success";
    }

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            MyAPIService apiService = new MyAPIService(jedis);
            // Simulate multiple API calls
            for (int i = 0; i < 10; i++) {
                System.out.println("REQUEST:: simulateHttpRequest No: " + i);
                String response = apiService.performOperation();
                System.out.println("Response: " + response);
            }
        }


    }
}
