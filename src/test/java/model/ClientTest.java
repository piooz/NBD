package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ClientTest {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    @BeforeAll
    static void beforeAll(){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    static void afterAll() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void clientToDatabase(){
        Client cli = new Client();
        cli.setFistName("Macius");

        entityManager.getTransaction().begin();
            entityManager.persist(cli);
        entityManager.getTransaction().commit();
    }

    @Test
    void getId() {
        long testval = 2;
        Client cli = new Client();
        cli.setId(testval);
        Assert.isTrue(cli.getId() == testval, "test not maching" );
    }
}