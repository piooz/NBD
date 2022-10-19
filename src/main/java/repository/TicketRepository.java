package repository;

import jakarta.persistence.EntityManager;
import model.Ticket;

public class TicketRepository extends Repository<Ticket> {

    private EntityManager em;

    TicketRepository(EntityManager entityManager) {
        super(entityManager);
    }


    public Ticket getById(int Id) {
        return em.find(Ticket.class, Id);
    }
}
