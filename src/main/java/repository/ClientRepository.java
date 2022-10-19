package repository;

import jakarta.persistence.EntityManager;
import model.Client;

public class ClientRepository extends Repository<Client> {

    private EntityManager em;
    ClientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Client getById(int Id) {
        return em.find(Client.class, Id);
    }
}
