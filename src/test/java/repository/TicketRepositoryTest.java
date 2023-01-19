package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.truncate.Truncate;
import model.CinemaDDL;
import model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    CqlSession session = CinemaDDL.initSession();

    TicketRepository ticketRepository = new TicketRepository(session);

    void flushTable() {
        Truncate statement = QueryBuilder.truncate("tickets");
        SimpleStatement ss = statement.build();
        session.execute(ss);
    }

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

        UUID uuid = new UUID(3,3);
        UUID uuid2 = new UUID(3,4);
        UUID uuid3 = new UUID(3,5);
        Ticket tick = new Ticket(uuid,uuid2,uuid3, 30,2, Ticket.TicketType.Normal.name());

        if(ticketRepository.get(uuid) == null) {
            ticketRepository.add(tick);
        }

        assertDoesNotThrow( () -> ticketRepository.remove(tick));

        assertNull(ticketRepository.get(uuid));
    }

    @Test
    void update() {

        UUID uuid = new UUID(3,3);
        UUID uuid2 = new UUID(3,4);
        UUID uuid3 = new UUID(3,5);
        Ticket tick = new Ticket(uuid,uuid2,uuid3, 30,2, Ticket.TicketType.Normal.name());
        Ticket tick2 = new Ticket(uuid,uuid2,uuid3, 40,1, Ticket.TicketType.Reduced.name());

        if(ticketRepository.get(uuid) == null) {
            ticketRepository.add(tick);
        }

        assertDoesNotThrow(() -> ticketRepository.update(tick2));

        assertEquals(tick2, ticketRepository.get(uuid));

    }

}