package repository;

import model.MovieMdb;
import model.NormalMdb;
import model.ShowMdb;
import model.TicketMdb;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    TicketRepository tr = new TicketRepository();
    ShowRepository sr = new ShowRepository();
    MovieRepository mr = new MovieRepository();

    @Test
    public void add() {
        // przechodzi
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show.getId());

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

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,1,3,mov);
        sr.add(show);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show.getId());

        assertTrue(tr.add(ticket));
    }

    @Test
    void addToFullShow() {
        tr.drop();
        mr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,0,3,mov);
        sr.add(show);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show.getId());

        assertFalse(tr.add(ticket));
    }

    @Test
    void remove() {
        tr.drop();
        mr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1, "UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,0,3,mov);
        sr.add(show);

        TicketMdb ticket = new NormalMdb(new ObjectId(), 2,3, show.getId());

        assertTrue(tr.add(ticket));
    }
}