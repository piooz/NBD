package repository;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    protected JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder().build();
    protected JedisPooled RedisClient;
    protected Jsonb jsonb;

    @Test
    public void SimpleTest() {

        this.jedisClientConfig = DefaultJedisClientConfig.builder().build();
        this.RedisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
        this.jsonb = JsonbBuilder.create();

        RedisClient.setex("1", 360, "ahahah");
        RedisClient.setex("1", 40, "zmaina");
        RedisClient.setnx("2","Yo2");

    }

}