package repository.kafka;

import Utils.KafkaTicketProducer;
import model.TicketMdb;
import org.bson.types.ObjectId;
import repository.IRepository;
import repository.RepositoryDecorator;

public class KafkaRepository extends RepositoryDecorator<TicketMdb> {

    KafkaTicketProducer kafkaTicketProducer = new KafkaTicketProducer();
    public KafkaRepository(IRepository<TicketMdb> ticketRepository) {
        super.repository = ticketRepository;
    }

    @Override
    public TicketMdb get(ObjectId id) {
        return super.repository.get(id);
    }

    @Override
    public boolean add(TicketMdb item) {
        kafkaTicketProducer.produceTicket(item);
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
