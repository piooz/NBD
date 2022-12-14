package managers;

import jakarta.persistence.*;
import model.Client;
import model.Movie;
import model.Show;
import model.Ticket;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Movie movie;
    private static Movie movie2;
    private static Show show;
    private static Show show2;
    private static Show show3;
    private static Client cli;
    private static Client cli2;

    @BeforeAll
    static void beforeAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
        show = new Show(1,30,30, movie);
        show2 = new Show(1,2,1, movie2);
        show3 = new Show(1,9,0, movie2);
        cli = new Client("Vati","Vati@gov.pl");
        cli2 = new Client("Adam","Adam@gov.pl");


        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(movie2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(show);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(show2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(cli);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(cli2);
        entityManager.getTransaction().commit();

    }




    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void addTicket() throws Exception {
        int before = show.getAvailableSeats();
        TicketManager tm = new TicketManager(entityManager);
        Ticket ticket = tm.addTicket(11,30, cli, show,false);

        List<Ticket> list = tm.findAll();
        assertTrue(list.contains(ticket));

        assertEquals(before - 1, show.getAvailableSeats());
    }

    @Test
    void addTicketThrow() {
        TicketManager tm = new TicketManager(entityManager);
    }

    @Test
    void addTicketConflict() throws Exception {
        TicketManager tm = new TicketManager(entityManager);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager em2 = entityManagerFactory.createEntityManager();

        em2.getTransaction().begin();
        Show sh = em2.find(Show.class, show2.getId());
        sh.setHallnumber(40);
        em2.getTransaction().commit();

        assertThrows(RollbackException.class, ()->tm.addTicket(11,30, cli, show2,true));
    }
}