package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import model.CinemaDDL;
import model.Client;
import model.Movie;
import model.Show;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowRepositoryTest {


    CqlSession session = CinemaDDL.initSession();
    ClientRepository clientRepository = new ClientRepository(session);
    MovieRepository movieRepository = new MovieRepository(session);
    ShowRepository showRepository = new ShowRepository(session);
    void flush() {
        session.execute("TRUNCATE table cinema.clients;");
    }

    @Test
    void get() {
        UUID uuid2 = new UUID(1,1);
        UUID uuid3 = new UUID(1,3);

        Movie movie = new Movie(uuid2,"UP","Animation","Nieznam");
        Show show = new Show(uuid3,2,5, uuid2);
        assertDoesNotThrow(() -> showRepository.add(show));

        assertEquals(show,showRepository.get(uuid3));
    }

    @Test
    void add() {
        UUID uuid2 = new UUID(1,1);
        UUID uuid3 = new UUID(1,3);

        Movie movie = new Movie(uuid2,"UP","Animation","Nieznam");
        Show show = new Show(uuid3,2,5, uuid2);

        assertDoesNotThrow(() -> showRepository.add(show));
    }

    @Test
    void remove() {
        UUID uuid2 = new UUID(1,1);
        UUID uuid3 = new UUID(1,3);

        Movie movie = new Movie(uuid2,"UP","Animation","Nieznam");
        Show show = new Show(uuid3,2,5, uuid2);

        assertDoesNotThrow(() -> showRepository.add(show));

        assertDoesNotThrow( () -> showRepository.remove(show));
    }

    @Test
    void update() {

        UUID uuid2 = new UUID(1,1);
        UUID uuid3 = new UUID(1,3);

        Movie movie = new Movie(uuid2,"DOWN","funny","Nieznam");
        Show show = new Show(uuid3,2,8, uuid2);

        showRepository.update(show);
    }

    @Test
    void find() {
    }

    @Test
    void getAll() {
    }
}