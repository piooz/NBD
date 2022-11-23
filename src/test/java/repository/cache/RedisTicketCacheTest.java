package repository.cache;

import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class RedisTicketCacheTest {

    TicketRepository ticketRepository = new TicketRepository();
    RedisTicketCache redisTicketCache = new RedisTicketCache(ticketRepository);

    @Test
    void get() {

    }

    @Test
    void add() {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show.getId(), cli.getClientID());
        assertTrue(redisTicketCache.add(ticket));

    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}