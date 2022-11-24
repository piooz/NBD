package repository.cache;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class RedisTicketCacheTest {

    protected JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder().build();
    protected Jsonb jsonb = JsonbBuilder.create();
    protected JedisPooled RedisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);

    TicketRepository ticketRepository = new TicketRepository();
    RedisTicketCache redisTicketCache = new RedisTicketCache(ticketRepository);

    @Test
    void getFromCache() {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();
        ObjectId id4 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(id4, 20,5, show, cli);
        assertTrue(redisTicketCache.add(ticket));

        TicketMdb foo = redisTicketCache.get(id4);

        assertEquals(foo, ticket);
    }
    @Test
    void getEmptyFromEmptyCache() {
        redisTicketCache.flushCache();

        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();
        ObjectId id4 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(id4, 20,5, show, cli);
        ticketRepository.add(ticket);

        TicketMdb foo = redisTicketCache.get(id4);
        assertEquals(foo, ticket);
    }

    @Test
    void add() {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(new ObjectId(), 20,5, show, cli);
        assertTrue(redisTicketCache.add(ticket));
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
        redisTicketCache.flushCache();

        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();
        ObjectId id4 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(id4, 20,5, show, cli);
        assertTrue(redisTicketCache.add(ticket));

        TicketMdb foo = redisTicketCache.remove(id4);

        assertEquals(foo, ticket);
    }

    @Test
    void flush() {
        redisTicketCache.flushCache();
    }
}