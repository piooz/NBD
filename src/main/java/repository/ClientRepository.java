package repository;

import jakarta.persistence.EntityManager;
import model.Client;

public class ClientRepository extends Repository<Client> {

    public ClientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Client getById(long Id) {
        return em.find(Client.class, Id);
    }
}
