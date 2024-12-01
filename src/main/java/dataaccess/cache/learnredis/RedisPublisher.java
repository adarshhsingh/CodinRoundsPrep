package dataaccess.cache.learnredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisPublisher {
    private static final Logger logger = LoggerFactory.getLogger(RedisPublisher.class);

    public static void main(String[] args) {

        try (Jedis jedis = new Jedis("localhost",6379)) {

            String value = jedis.get("name");
            logger.info("Able to test Jedis instance");
            logger.info("value = " + value);


            String channel = "channel";
            String message = "Hello, World!";


            System.out.println("HAHAHA: ");

            jedis.publish(channel, message);
            System.out.println("Message published: " + message);
        }
    }
}
