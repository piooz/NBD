package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends Repository<Client> {

    public ClientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Client> findAll() {
        List<Client> list;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);
        From<Client,Client> from = query.from(Client.class);
        query.select(from);
        list = em.createQuery(query).getResultList();

        return list;
    }

    @Override
    public Client getById(long Id) {
        return em.find(Client.class, Id);
    }
}
