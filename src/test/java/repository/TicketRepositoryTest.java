package repository;

import com.mongodb.MongoCommandException;
import model.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    TicketRepository tr = new TicketRepository();
    ShowRepository sr = new ShowRepository();
    MovieRepository mr = new MovieRepository();
    ClientRepository cr = new ClientRepository();

    @Test
    public void add() {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");
        cr.add(cli);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show, cli);

        assertTrue(tr.add(ticket));
        ArrayList<ShowMdb> ls = sr.find(id2);
        if(!ls.isEmpty()) {
            assertEquals(1,ls.get(0).getAvailableSeats() );
        }
        else {
            fail();
        }
    }

    @Test
    void addToEmptyCollection() {
        tr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,1,3,mov);
        sr.add(show);
        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");
        cr.add(cli);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show, cli);

        assertTrue(tr.add(ticket));
    }

    @Test
    void addToFullShow() {
        tr.drop();
        mr.drop();
        cr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,0,3,mov);
        sr.add(show);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");
        cr.add(cli);
        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show, cli);

        assertFalse(tr.add(ticket));
    }

    @Test
    public void remove() {
        tr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        ObjectId id3 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);

        ClientMdb cli = new ClientMdb(id3,"Jaworek", "Jacek@wp.pl");
        cr.add(cli);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show, cli);

        assertTrue(tr.add(ticket));
        ArrayList<ShowMdb> ls = sr.find(id2);
        if(!ls.isEmpty()) {
            assertEquals(1,ls.get(0).getAvailableSeats() );
        }
        else {
            fail();
        }

        TicketMdb fromDB = tr.remove(ticket.getId());

        ArrayList<ShowMdb> list = sr.find(id2);
        if(!ls.isEmpty()) {
            assertEquals(2, list.get(0).getAvailableSeats() );
        }
        else {
            fail();
        }
    }

    @Test
    public void removeException() {
        tr.drop();
        assertThrows(AssertionError.class, ()-> tr.remove(new ObjectId()));
    }
}