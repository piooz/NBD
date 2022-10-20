package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import model.Show;
import model.Ticket;

import java.util.List;

public class TicketRepository extends Repository<Ticket> {

    public TicketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> list;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);
        From<Ticket,Ticket> from = query.from(Ticket.class);
        query.select(from);
        list = em.createQuery(query).getResultList();
        return list;
    }


    public Ticket getById(long Id) {
        em.getTransaction().begin();
        Ticket ticket =  em.find(Ticket.class, Id);
        em.getTransaction().commit();
        return ticket;
    }
}
