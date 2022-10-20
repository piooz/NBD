package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Client client;
    private static Client client2;

    @BeforeAll
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @BeforeAll
    static void setClient() {
        client = new Client("Helena", "Henia@pw.lp");
        client2 = new Client("Adam", "mirek@onet.pl");
    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void add() {
        ClientRepository cr = new ClientRepository(entityManager);
        cr.add(client);
        assertTrue(entityManager.contains(client));
        cr.add(client2);
        assertTrue(entityManager.contains(client2));
    }

    @Test
    void remove() {
        ClientRepository cr = new ClientRepository(entityManager);
        cr.add(client);
        assertTrue(entityManager.contains(client));
        cr.remove(client);
        assertFalse(entityManager.contains(client));
    }

    @Test
    void getById() {
        ClientRepository cr = new ClientRepository(entityManager);
        client.setId(30);
        cr.add(client);
        assertTrue(entityManager.contains(client));
        Client ToJa = cr.getById(30);
        assertSame(ToJa, client);
    }

    @Test
    void findAll() {
        ClientRepository cr = new ClientRepository(entityManager);
        cr.add(client);
        cr.add(client2);
        assertTrue(entityManager.contains(client));
        List<Client> list = cr.findAll();
        assertTrue(list.size() == 2);
    }
}