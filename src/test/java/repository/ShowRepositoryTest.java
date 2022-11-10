package repository;

import model.ClientMdb;
import model.MovieMdb;
import model.ShowMdb;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowRepositoryTest {

    private ShowRepository sr = new ShowRepository();
    private MovieRepository mr = new MovieRepository();

    @Test
    void add() throws InterruptedException {
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb("UP" ,"Animation","Novak" );
        mr.add(mov);

        ShowMdb show = new ShowMdb(id2, 4,2,3, mov);
        sr.add(show);
        assertTrue(true);
    }

    @Test
    void remove() {
        sr.drop();
        mr.drop();
        ObjectId id1 = new ObjectId();
        ObjectId id2 = new ObjectId();
        MovieMdb mov = new MovieMdb(id1,"UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);
        sr.remove(id2);
        ArrayList<ShowMdb> ls2 = sr.findAll();
        assertEquals(0, ls2.size());
    }

    @Test
    void dropAllShows() {
        sr.drop();
        ArrayList<ShowMdb> list = sr.findAll();
        assertEquals(0, list.size());
    }

    @Test
    void findAll() {
        mr.drop();
        sr.drop();
        MovieMdb mov1 = new MovieMdb(new ObjectId(), "Joker" ,"Drama","Phillips" );
        MovieMdb mov2 = new MovieMdb(new ObjectId(), "Tybetanscy mnisi" ,"Dramat","Life" );
        mr.add(mov1);
        mr.add(mov2);
        ShowMdb show = new ShowMdb(new ObjectId(), 4,2,3,mov1);
        ShowMdb show1 = new ShowMdb(new ObjectId(), 10,2,4,mov1);
        sr.add(show);
        sr.add(show1);
        ArrayList<ShowMdb> list = sr.findAll();
        assertEquals(show1, list.get(1));
        assertEquals(2,list.size());
    }

    @Test
    void find() throws InterruptedException {
        sr.drop();
        mr.drop();
        ObjectId id1 = new ObjectId(new Timestamp(System.currentTimeMillis()));
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1,"UP" ,"Animation","Novak" );
        mr.add(mov);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);


        ArrayList<ShowMdb> ls = sr.find(id2);
        ShowMdb sh = ls.get(0);


        assertEquals(sh,show);
        assertEquals(mov,show.getMovieMdb());

        ArrayList<MovieMdb> ls2 = mr.find(sh.getMovieMdb().getId());
        assertEquals(1,ls.size());
    }
}