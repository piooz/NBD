package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Client;
import model.Movie;
import model.Show;
import model.Ticket;
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
    private static Ticket ticket;
    private static Ticket ticket2;

    @BeforeAll
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        client = new Client("Helena", "Henia@pw.lp");
        client2 = new Client("Adam", "mirek@onet.pl");
        movie = new Movie("Space Odyssey",200,"sci-fi","Kubrick");
        movie2 = new Movie("Batman Begins",230, "action","Nolan");
        show = new Show(1,30,30, movie);
        show2 = new Show(1,2,1, movie2);
        ticket = new Ticket(1,20, client, show);
        ticket2 = new Ticket(2,40, client2, show2);
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
        tr.add(ticket);
        assertTrue(entityManager.contains(ticket));
    }

    @Test
    void remove() {
    }
}