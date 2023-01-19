package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import model.CinemaDDL;
import model.Movie;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    CqlSession session = CinemaDDL.initSession();
    MovieRepository movieRepository = new MovieRepository(session);

    void flush() {
        session.execute("TRUNCATE table cinema.clients;");
    }
    @Test
    void get() {
        UUID uuid = new UUID(3,3);
        Movie movie = new Movie(uuid,"Lsnienie", "Horror","Kubrick");

        assertEquals(movie, movieRepository.get(uuid));
    }

    @Test
    void getNotExisting() {

        assertNull(movieRepository.get(UUID.randomUUID()));
    }

    @Test
    void add() {
        Movie movie = new Movie(new UUID(3,3),"Lsnienie", "Horror","Kubrick");
        assertDoesNotThrow(() -> movieRepository.add(movie));
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

    @Test
    void getAll() {
    }
}