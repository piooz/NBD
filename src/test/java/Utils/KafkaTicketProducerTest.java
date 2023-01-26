package Utils;

import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class KafkaTicketProducerTest {

    KafkaTicketProducer kafkaTicketProducer = new KafkaTicketProducer();

    @Test
    void createTopic() throws InterruptedException {
        KafkaTicketProducer.createTopic("tickets", 3, (short)3);
    }

    @Test
    void isTopicExisting() {
        assertTrue(KafkaTicketProducer.isTopicExisting("tickets"));
    }

    @Test
    void isTopicExistingFalse() {
        assertFalse(KafkaTicketProducer.isTopicExisting("RandomBS"));
    }

    @Test
    void produceTicket() throws ExecutionException, InterruptedException {
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