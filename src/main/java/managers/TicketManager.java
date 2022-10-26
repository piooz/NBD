package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import model.*;
import repository.ShowRepository;
import repository.TicketRepository;

import java.util.List;

public class TicketManager {

    private final TicketRepository tr;
    private EntityManager em;
    private EntityTransaction et;

    public TicketManager(EntityManager entityManager) {
        em = entityManager;
        tr = new TicketRepository(entityManager);
    }

    public void removeTicket(long Id) {
        et = em.getTransaction();
        et.begin();
        tr.remove(tr.getById(Id));
        et.commit();
    }

    public List<Ticket> findAll() {

        et = em.getTransaction();
        et.begin();
        List<Ticket> ls = tr.findAll();
        et.commit();
        return ls;
    }
    public Ticket getTicket(long Id) {

        et = em.getTransaction();
        et.begin();
        Ticket t = tr.getById(Id);
        et.commit();
        return t;
    }

    public List<Ticket> findTicket(CriteriaQuery<Ticket> query) {

        et = em.getTransaction();
        et.begin();
        List<Ticket> ls = tr.findByQuery(query);
        et.commit();
        return ls;
    }

    public Ticket addTicket(int seatNumber, float price, Client client, Show show, Boolean reduced) throws Exception {
        Ticket ticket;
        if(show.getAvailableSeats() > 0) {
            et = em.getTransaction();
            et.begin();
            show.setAvailableSeats(show.getAvailableSeats() - 1);
            if(reduced) {
                ticket = new Reduced(seatNumber, price, client, show);
            }
            else {
                ticket = new Normal(seatNumber, price, client, show);
            }
            tr.add(ticket);
            et.commit();

            return ticket;
        } else {
            throw new Exception("Show has no more available seats");
        }
    }
}
