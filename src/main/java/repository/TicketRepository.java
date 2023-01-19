package repository;

import com.datastax.oss.driver.api.core.CqlSession;
import dao.TicketDao;
import mappers.TicketMapper;
import mappers.TicketMapperBuilder;
import model.CinemaDDL;
import model.Ticket;

import java.util.List;
import java.util.UUID;

public class TicketRepository implements IRepository<Ticket> {

    CqlSession session = CinemaDDL.initSession();

    TicketMapper ticketMapper;
    TicketDao ticketDao;

    public TicketRepository(CqlSession session) {
        this.session = session;
        ticketMapper = new TicketMapperBuilder(session).build();
        ticketDao = ticketMapper.ticketDao();
    }

    @Override
    public Ticket get(UUID id) {
        return ticketDao.getById(id);
    }

    @Override
    public void add(Ticket elements) {
        ticketDao.createTicket(elements);
    }

    @Override
    public void remove(Ticket elements) {
        ticketDao.deleteShow(elements);
    }

    @Override
    public void update(Ticket elements) {
        ticketDao.updateTicket(elements);
    }

    @Override
    public List<Ticket> find(Object elements) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }
}