package repository.kafka;

import model.TicketMdb;
import org.bson.types.ObjectId;
import repository.IRepository;
import repository.RepositoryDecorator;

public class kafkaRepository extends RepositoryDecorator<TicketMdb> {


    public kafkaRepository(IRepository<TicketMdb> ticketRepository) {
        super.repository = ticketRepository;
    }

    @Override
    public TicketMdb get(ObjectId id) {
        return null;
    }

    @Override
    public boolean add(TicketMdb item) {
        return false;
    }

    @Override
    public void update(TicketMdb item) {

    }

    @Override
    public TicketMdb remove(ObjectId id) {
        return null;
    }
}
