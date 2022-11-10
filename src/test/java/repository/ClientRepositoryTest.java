package repository;

import model.ClientMdb;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    private ClientRepository cr = new ClientRepository();
    @Test
    void add() {
        ClientMdb cli = new ClientMdb("Jaworek", "Jacek@wp.pl");
        ClientMdb cli2 = new ClientMdb("Kawiorek", "Kawiorek@wp.pl");
        cr.add(cli);
        cr.add(cli2);
        ArrayList<ClientMdb> ls = cr.findAll();
        assertEquals(2, ls.size());

    }

    @Test
    void drop() {
        cr.drop();
        ArrayList<ClientMdb> list = cr.findAll();
        assertEquals(0, list.size());
    }

    @Test
    void remove() {
        cr.drop();
        ObjectId id = new ObjectId();

        ClientMdb cli = new ClientMdb(id, "Jaworek", "Jacek@wp.pl");
        ClientMdb cli2 = new ClientMdb("Marek", "mareczek@wp.pl");
        cr.add(cli);
        cr.add(cli2);

        ClientMdb removed = cr.remove(id);
        assertEquals(removed, cli);

        ArrayList<ClientMdb> ls = cr.findAll();
        assertEquals(1, ls.size());
    }

    @Test
    void findAll() {
        cr.drop();

        ObjectId id = new ObjectId();
        ClientMdb cli = new ClientMdb(id, "Jaworek", "Jacek@wp.pl");
        ClientMdb cli2 = new ClientMdb("Marek", "mareczek@wp.pl");
        cr.add(cli);
        cr.add(cli2);

        ArrayList<ClientMdb> list = cr.findAll();

        assertEquals(cli, list.get(0));
        assertEquals(cli2, list.get(1));
    }

    @Test
    void findById() {
        cr.drop();
        ObjectId id = new ObjectId();
        ClientMdb cli = new ClientMdb(id, "Jaworek", "Jacek@wp.pl");
        cr.add(cli);
        ArrayList<ClientMdb> ls = cr.find(id);

        assertEquals(1, ls.size());
        assertTrue(ls.contains(cli));
    }
}