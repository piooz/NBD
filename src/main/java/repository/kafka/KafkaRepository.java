package repository.kafka;

import Utils.KafkaProducer;
import model.TicketMdb;
import org.bson.types.ObjectId;
import repository.IRepository;
import repository.RepositoryDecorator;

public class KafkaRepository extends RepositoryDecorator<TicketMdb> {

    KafkaProducer kafkaProducer = new KafkaProducer();
    public KafkaRepository(IRepository<TicketMdb> ticketRepository) {
        super.repository = ticketRepository;
    }

    @Override
    public TicketMdb get(ObjectId id) {
        return super.repository.get(id);
    }

    @Override
    public boolean add(TicketMdb item) {
        kafkaProducer.produceTicket(item);
        return true;
//        return super.repository.add(item);
    }

    @Override
    public void update(TicketMdb item) {
        super.repository.update(item);
    }

    @Override
    public TicketMdb remove(ObjectId id) {
        return super.repository.remove(id);
    }
}
