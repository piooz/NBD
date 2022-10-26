package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import model.Client;
import model.Normal;
import model.Show;
import model.Ticket;
import repository.ShowRepository;
import repository.TicketRepository;

import java.util.List;

public class TicketManager {

    private final TicketRepository tr;
    private ShowRepository sr;
    private EntityManager em;

    public TicketManager(EntityManager entityManager) {
        em = entityManager;
        tr = new TicketRepository(entityManager);
        sr = new ShowRepository(entityManager);
    }

    public void removeTicket(long Id) {
        tr.remove(tr.getById(Id));
    }

    public List<Ticket> findAll() {
        return tr.findAll();
    }
    public Ticket getTicket(long Id) {
        return tr.getById(Id);
    }

    public List<Ticket> findTicket(CriteriaQuery<Ticket> query) {
        return tr.findByQuery(query);
    }

    public Ticket addTicket(int seatNumber, float price, Client client, Show show) throws Exception {
        if(show.getAvailableSeats() > 0) {
            show.setAvailableSeats(show.getAvailableSeats() - 1);
            sr.add(show);

            Normal ticket = new Normal(seatNumber, price, client, show);
            tr.add(ticket);
            return ticket;
        } else {
            throw new Exception("Show has no more available seats");
        }
    }
}
