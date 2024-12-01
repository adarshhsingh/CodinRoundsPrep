package dataaccess.cache.learnredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(RedisSubscriber.class);

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        String channel = "channel";

        String value = jedis.get("name");
        logger.info("Able to test Jedis instance");
        logger.info("value = " + value);

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Received message: " + message);
            }
        }, channel);
    }
}

