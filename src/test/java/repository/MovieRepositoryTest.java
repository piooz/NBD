package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Movie movie;
    private static Movie movie2;

    @BeforeAll
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void add() {
        MovieRepository mr = new MovieRepository(entityManager);
        mr.add(movie);
        assertTrue(entityManager.contains(movie));
        mr.add(movie2);
        assertTrue(entityManager.contains(movie2));
    }

    @Test
    void remove() {
        MovieRepository mr = new MovieRepository(entityManager);
        mr.add(movie);
        assertTrue(entityManager.contains(movie));
        mr.remove(movie);
        assertFalse(entityManager.contains(movie));
    }

    @Test
    void getById() {
        MovieRepository mr = new MovieRepository(entityManager);
        movie.setId(10);
        mr.add(movie);
        assertTrue(entityManager.contains(movie));
        Movie toTy = mr.getById(10);
        assertSame(toTy, movie);
    }
}