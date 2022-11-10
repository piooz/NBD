package repository;

import model.ClientMdb;
import model.MovieMdb;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    private MovieRepository mr = new MovieRepository();

    @Test
    void add() {
        MovieMdb movie = new MovieMdb(new ObjectId(), "Joker" ,"Drama","Phillips" );
        MovieMdb movie2 = new MovieMdb(new ObjectId(), "Big Lebowski", "comedy","Coen");
        mr.add(movie);
        mr.add(movie2);
        ArrayList<MovieMdb> ls = mr.findAll();
        assertEquals(2, ls.size());

    }

    @Test
    void remove() {
        mr.drop();
        ObjectId id1 = new ObjectId();

        MovieMdb movie1 = new MovieMdb(id1, "Joker" ,"Drama","Phillips" );
        MovieMdb movie2 = new MovieMdb(new ObjectId(), "Big Lebowski", "comedy","Coen");
        mr.add(movie1);
        mr.add(movie2);

        mr.remove(id1);
        ArrayList<MovieMdb> ls2 = mr.findAll();
        assertEquals(1, ls2.size());


    }

    @Test
    void dropAllMovies() {
        mr.drop();
        ObjectId id1 = new ObjectId();
        MovieMdb movie1 = new MovieMdb(id1, "Joker" ,"Drama","Phillips" );
        mr.add(movie1);
        mr.drop();
        ArrayList<MovieMdb> list = mr.findAll();
        assertEquals(0, list.size());

    }

    @Test
    void findAll() {
        mr.drop();
        MovieMdb movie1 = new MovieMdb(new ObjectId(), "Joker" ,"Drama","Phillips" );
        MovieMdb movie2 = new MovieMdb(new ObjectId(), "Tybetanscy mnisi" ,"Dramat","Life" );
        MovieMdb movie3 = new MovieMdb(new ObjectId(), "Tafla wody" ,"Dramat","Jezus" );
        mr.add(movie1);
        mr.add(movie2);
        mr.add(movie3);

        ArrayList<MovieMdb> list = mr.findAll();
        assertEquals(movie3, list.get(2));
        assertEquals(3,list.size());

    }

    @Test
    void find() {
        mr.drop();
        ObjectId id1 = new ObjectId();
        MovieMdb movie1 = new MovieMdb(id1, "Joker" ,"Drama","Phillips" );
        mr.add(movie1);
        ArrayList<MovieMdb> ls = mr.find(id1);
        assertEquals(1, ls.size());
        assertTrue(ls.contains(movie1));
    }
}