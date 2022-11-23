package repository;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    protected JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder().build();
    protected Jsonb jsonb = JsonbBuilder.create();
    protected JedisPooled RedisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);

    @Test
    public void SimpleTest() {

        ObjectId id = new ObjectId();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");


        TicketMdb tic = new NormalMdb(id, 20,10, show.getId(),cli.getClientID());

        TicketMdb ticket = RedisClient.jsonGet("ticket:"+id.toHexString(), TicketMdb.class);
        assertNull(ticket);

        RedisClient.jsonSet("ticket:" + id.toHexString(), jsonb.toJson(tic));
        RedisClient.expire("ticket:" + id.toHexString(), 1200);

        RedisClient.setex("1", 360, "ahahah");
        RedisClient.setex("1", 40, "zmaina");
        RedisClient.setnx("2","Yo2");

    }

    @Test
    public void TicketTest() {

        ObjectId id = new ObjectId();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb tic = new NormalMdb(id, 20,10,show.getId(), cli.getClientID());

        RedisClient.jsonSet("ticket:" + id.toHexString(), jsonb.toJson(tic));
        RedisClient.expire("ticket:" + id.toHexString(), 1200);
    }

}