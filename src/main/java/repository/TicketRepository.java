package repository;

import jakarta.persistence.EntityManager;
import model.Ticket;

public class TicketRepository extends Repository<Ticket> {

    TicketRepository(EntityManager entityManager) {
        super(entityManager);
    }


    public Ticket getById(long Id) {
        return em.find(Ticket.class, Id);
    }
}
