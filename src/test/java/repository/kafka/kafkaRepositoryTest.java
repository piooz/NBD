package repository.kafka;

import Utils.KafkaTicketProducer;
import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

class kafkaRepositoryTest {

//    protected JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder().build();
//    protected Jsonb jsonb = JsonbBuilder.create();
//    protected JedisPooled RedisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
//
//    TicketRepository ticketRepository = new TicketRepository();
//    RedisTicketCache redisTicketCache = new RedisTicketCache(ticketRepository);
//
//    KafkaRepository kafkaRepository = new KafkaRepository(redisTicketCache);

    KafkaTicketProducer kafkaTicketProducer = new KafkaTicketProducer();

    @Test
    void add() {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show, cli);

        kafkaTicketProducer.produceTicket(ticket);
    }
}