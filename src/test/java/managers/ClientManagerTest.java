package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Client;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientManagerTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static Client client;
    private static Client client2;
    private static int testID = 30;

    private static ClientManager cm;

    @Before
    static void beforeAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();

        cm = new ClientManager(entityManager);


        client = new Client("Helena", "mirinda@onet.pl");
        client.setId(testID);
        client2 = new Client("Adam", "mirek@onet.pl");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
    @Test
    void removeClient() {
        entityManager.getTransaction().begin();
        entityManager.persist(client2);
        entityManager.getTransaction().commit();

        assertEquals(cm.findAll().size(),2);
        cm.removeClient(testID);
        assertEquals(cm.findAll().size(),1);
    }

    @Test
    void findAll() {
        entityManager.getTransaction().begin();
        entityManager.persist(client2);
        entityManager.getTransaction().commit();


        List<Client> list = cm.findAll();

        assertEquals(list.size(),2);
        assertTrue(list.contains(client));
        assertTrue(list.contains(client2));

    }

    @Test
    void getClient() {
        Client cli = cm.getClient(testID);
        assertSame(cli,client);
    }

    @Test
    void findClient() {

    }

    @Test
    void addClient() throws Exception {
        Client maciej = cm.addClient("Maciej", "mm@wp.pl");
        List<Client> list = cm.findAll();
        assertTrue(list.contains(maciej));
    }

    @Test
    void addClientExcepion() {
        Exception exception = assertThrows(Exception.class, () -> cm.addClient("Maciej", "mirinda@onet.pl"));
        assertEquals("Email already taken", exception.getMessage());
    }
}