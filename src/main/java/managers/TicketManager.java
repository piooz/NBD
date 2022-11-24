package managers;

import model.*;
import org.bson.types.ObjectId;
import repository.TicketRepository;
import repository.cache.RedisTicketCache;

import java.util.List;

public class TicketManager {

    final private TicketRepository ticketRepository;
    final private RedisTicketCache repositoryDecorator;

    public TicketManager() {
        ticketRepository = new TicketRepository();
        repositoryDecorator = new RedisTicketCache(ticketRepository);
    }

    public boolean createTicket(ClientMdb client, ShowMdb show, int seatNumber, float price, boolean isReduced) {
        if(isReduced) {
            return repositoryDecorator.add(new ReducedMdb(new ObjectId(), price, seatNumber,show, client));
        }
        else {
            return repositoryDecorator.add(new NormalMdb(new ObjectId(), price, seatNumber,show, client));
        }
    }

    public TicketMdb removeTicket(ObjectId id) {
        return repositoryDecorator.remove(id);
    }

    public List<TicketMdb> findAll() {
        return ticketRepository.findAll();
    }
}
