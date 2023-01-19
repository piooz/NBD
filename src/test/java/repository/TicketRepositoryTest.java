package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import model.CinemaDDL;
import model.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

class TicketRepositoryTest {

    CqlSession session = CinemaDDL.initSession();

    TicketRepository ticketRepository = new TicketRepository(session);

    @Test
    void get() {
        UUID uuid = new UUID(3,3);
        UUID uuid2 = new UUID(3,4);
        UUID uuid3 = new UUID(3,5);
        Ticket ticket = new Ticket(uuid,uuid2,uuid3, 30,2, Ticket.TicketType.Normal.name());

        assertDoesNotThrow(() -> ticketRepository.add(ticket));

        assertEquals(ticket,ticketRepository.get(uuid));
    }

    @Test
    void add() {
        UUID uuid = new UUID(3,3);
        UUID uuid2 = new UUID(3,4);
        UUID uuid3 = new UUID(3,5);
        Ticket ticket = new Ticket(uuid,uuid2,uuid3, 30,2, Ticket.TicketType.Normal.name());

        assertDoesNotThrow(() -> ticketRepository.add(ticket));
    }

    @Test
    void remove() {
    }

    @Test
    void update() {
    }

    @Test
    void find() {
    }
}