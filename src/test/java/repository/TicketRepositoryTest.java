package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Client;
import model.Movie;
import model.Show;
import model.Ticket;
import model.Normal;
import model.Reduced;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Client client;
    private static Client client2;
    private static Show show;
    private static Show show2;
    private static Movie movie;
    private static Movie movie2;
    private static Ticket ticket2;
    private static Reduced ticket1;

    @BeforeAll
    static void beforeAll(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        client = new Client("Helena", "Henia@pw.lp");
        client2 = new Client("Adam", "mirek@onet.pl");
        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
        show = new Show(1,30,30, movie);
        show2 = new Show(1,2,1, movie2);
        ticket1 = new Reduced(1,1,client,show );
        ticket2 = new Normal(2,1,client2,show2);
    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void add() {

        {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(show);
            entityManager.getTransaction().commit();
        }

        TicketRepository tr = new TicketRepository(entityManager);
        tr.add(ticket1);
        assertTrue(entityManager.contains(ticket1));
    }

    @Test
    void remove() {
        {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(show);
            entityManager.getTransaction().commit();

        }
        TicketRepository tr = new TicketRepository(entityManager);
        tr.add(ticket1);
        assertTrue(entityManager.contains(ticket1));

        EntityTransaction et = entityManager.getTransaction();
        et.begin();
        tr.remove(ticket1);
        et.commit();
        assertFalse(entityManager.contains(ticket1));
    }

    @Test
    void getById() {
        {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(movie);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            entityManager.persist(show);
            entityManager.getTransaction().commit();

        }
        TicketRepository tr = new TicketRepository(entityManager);
        ticket1.setId(130);
        tr.add(ticket1);
        assertTrue(entityManager.contains(ticket1));
        Ticket toTy = tr.getById(130);
        assertSame(toTy, ticket1);
    }
}