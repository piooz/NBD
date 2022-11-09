package repository;

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
        Thread.sleep(2000);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);
        assertTrue(true);
    }

    @Test
    void remove() {
    }

    @Test
    void dropAllClients() {
    }

    @Test
    void findAll() {
    }

    @Test
    void find() throws InterruptedException {
        sr.drop();
        mr.drop();
        ObjectId id1 = new ObjectId(new Timestamp(System.currentTimeMillis()));
        ObjectId id2 = new ObjectId();

        MovieMdb mov = new MovieMdb(id1,"UP" ,"Animation","Novak" );
        mr.add(mov);
        Thread.sleep(2000);
        ShowMdb show = new ShowMdb(id2, 4,2,3,mov);
        sr.add(show);


        ArrayList<ShowMdb> ls = sr.find(id2);
        ShowMdb sh = ls.get(0);
        System.out.print(sh.getMovieMdb().toString());

        assertEquals(sh,show);
        assertEquals(mov,show.getMovieMdb());

        ArrayList<MovieMdb> ls2 = mr.find(sh.getMovieMdb().getId());
        assertEquals(1,ls.size());
    }
}