package repository;

import model.MovieMdb;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    private MovieRepository mr = new MovieRepository();

    @Test
    void add() {
        MovieMdb movie = new MovieMdb(new ObjectId(), "Big Lebowski", "comedy","Coen");
        mr.add(movie);
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
    void find() {

    }
}