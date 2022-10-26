package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Client;
import repository.ClientRepository;

import java.util.List;

public class ClientManager {
    private final ClientRepository cr;
    private EntityManager em;

    public ClientManager(EntityManager entityManager) {
        em = entityManager;
        cr = new ClientRepository(entityManager);
    }

    public void removeClient(long Id) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        cr.remove(cr.getById(Id));
        et.commit();
    }

    public List<Client> findAll() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Client> list = cr.findAll();
        et.commit();
        return list;
    }
    public Client getClient(long Id) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Client cli =  cr.getById(Id);
        et.commit();
        return cli;
    }

    public List<Client> findClient(CriteriaQuery<Client> query) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Client> list = cr.findByQuery(query);
        et.commit();
        return list;
    }

    public Client addClient(String firstName, String email) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);

        EntityTransaction et = em.getTransaction();
        List<Client> list = findClient(cq.select(root).where(cb.like(root.get("email"),email)));

        if (list.isEmpty()) {
            et.begin();
            Client cli = new Client(firstName, email);
            cr.add(cli);
            et.commit();
            return cli;
        } else {
            throw new Exception("Email already taken");
        }
    }
}
