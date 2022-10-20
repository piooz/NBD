package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
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
        cr.remove(cr.getById(Id));
    }

    public List<Client> findAll() {
        return cr.findAll();
    }
    public Client getClient(long Id) {
        return cr.getById(Id);
    }

    public List<Client> findClient(CriteriaQuery<Client> query) {
        return cr.findByQuery(query);
    }

    public Client addClient(String firstName, String email) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        List<Client> list = findClient(cq.select(root).where(cb.like(root.get("email"),email)));

        if (list.isEmpty()) {
            Client cli = new Client(firstName, email);
            cr.add(cli);
            return cli;
        } else {
            throw new Exception("Email already taken");
        }
    }
}
