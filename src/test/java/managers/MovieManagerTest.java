package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieManagerTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Movie movie;
    private static Movie movie2;
    private static int testID = 30;

    private static MovieManager mm;

    @BeforeAll
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

        mm = new MovieManager(entityManager);


        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
        movie.setId(testID);

        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();

    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
    @Test
    void removeMovie() {
        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.getTransaction().commit();

        assertEquals(mm.findAll().size(),2);
        mm.removeMovie(testID);
        assertEquals(mm.findAll().size(),1);
    }

    @Test
    void findAll() {
        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.getTransaction().commit();


        List<Movie> list = mm.findAll();

        assertEquals(list.size(),2);
        assertTrue(list.contains(movie));
        assertTrue(list.contains(movie2));
    }

    @Test
    void getMovie() {
        Movie mv = mm.getMovie(testID);
        assertSame(movie,mv);
    }

    @Test
    void findMovie() {
    }

    @Test
    void addMovie() {
        Movie sr = mm.addMovie("The Showshank Redeption", 300, "drama", "Darabont");
        List<Movie> list = mm.findAll();
        assertTrue(list.contains(sr));
    }
}