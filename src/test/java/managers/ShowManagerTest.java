package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Movie;
import model.Show;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowManagerTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static int testID = 30;

    private static ShowManager sm;
    private static Movie movie;
    private static Movie movie2;
    private static Show show;
    private static Show show2;

    @BeforeAll
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

        sm = new ShowManager(entityManager);


        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
        show = new Show(1,30,30, movie);
        show.setId(testID);
        show2 = new Show(1,2,1, movie2);

        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(show);
        entityManager.getTransaction().commit();

    }
    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void removeShow() {
        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.persist(show2);
        entityManager.getTransaction().commit();

        assertEquals(sm.findAll().size(),2);
        sm.removeShow(testID);
        assertEquals(sm.findAll().size(),1);
    }

    @Test
    void findAll() {
        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.persist(show2);
        entityManager.getTransaction().commit();

        List<Show> list = sm.findAll();

        assertEquals(list.size(),2);
        assertTrue(list.contains(show));
        assertTrue(list.contains(show2));
    }

    @Test
    void getShow() {
        Show sh = sm.getShow(testID);
        assertSame(sh,show);
    }

    @Test
    void addShow() {
        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.getTransaction().commit();
        sm.addShow(2, 30, 2, movie2);
    }
}