package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Movie;
import model.Show;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowRepositoryTest {
    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    private static Movie movie;
    private static Movie movie2;
    private static Show show;
    private static Show show2;

    @BeforeEach
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

    }

    @BeforeEach
    void persist()
    {
        movie = new Movie("Space Odyssey", 200, "sci-fi", "Kubrick");
        movie2 = new Movie("Batman Begins", 230, "action", "Nolan");
        show = new Show(1, 30, 30, movie);
        show2 = new Show(1, 2, 1, movie2);

        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.getTransaction().commit();
    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void add() {
        ShowRepository sr = new ShowRepository(entityManager);
        sr.add(show);
        assertTrue(entityManager.contains(show));
        sr.add(show2);
        assertTrue(entityManager.contains(show2));
    }

    @Test
    void remove() {
        ShowRepository sr = new ShowRepository(entityManager);
        sr.add(show);
        assertTrue(entityManager.contains(show));
        sr.remove(show);
        assertFalse(entityManager.contains(show));
    }

    @Test
    void getById() {
        ShowRepository sr = new ShowRepository(entityManager);
        show.setId(838);
        sr.add(show);
        assertTrue(entityManager.contains(show));
        Show toTy = sr.getById(838);
        assertSame(toTy, show);
    }
}