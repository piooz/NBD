package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Statement;
import model.CinemaDDL;
import model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    CqlSession session = CinemaDDL.initSession();
    ClientRepository clientRepository = new ClientRepository(session);

    void flush() {
        session.execute("TRUNCATE table cinema.clients;");
    }
    @Test
    void get() {
        UUID uuid = new UUID(2,4);
        Client cli = new Client(uuid,"nowak","nowak@wp.pl");
        Client cli2 = new Client(new UUID(4,4),"nowak","nowak@wp.pl");
        Client cli3 = clientRepository.get(uuid);

        assertEquals(cli, cli3);
    }

    @Test
    void get_not_exist() {
        Client cli2 = clientRepository.get(UUID.randomUUID());
        assertNull(cli2);
    }

    @Test
    void add() {
        Client cli = new Client(new UUID(2,4),"nowak","nowak@wp.pl");
        clientRepository.add(cli);
    }

    @Test
    void remove() {
        UUID uuid = new UUID(2,4);
        Client cli = new Client(uuid,"nowak","nowak@wp.pl");
        clientRepository.remove(cli);
    }

    @Test
    void update() {
        UUID uuid = new UUID(2,4);
        Client cli = new Client(uuid,"nowak","nowak@wp.pl");
        clientRepository.add(cli);

        Client cli2 = new Client(uuid,"Marcysiek","Marian@wp.pl");
        clientRepository.update(cli2);

        assertEquals(cli2, clientRepository.get(uuid));
    }

    @Test
    void update_negative() {
        UUID uuid = new UUID(2,4);
        UUID uuid2 = new UUID(1,3);
        Client cli = new Client(uuid,"nowak","nowak@wp.pl");
        clientRepository.add(cli);

        Client cli2 = new Client(uuid2,"Marcysiek","Marian@wp.pl");
        clientRepository.update(cli2);

        assertNotEquals(clientRepository.get(uuid), clientRepository.get(uuid2));
    }

    @Test
    void find() {
    }

    @Test
    void getAll() {

    }
}