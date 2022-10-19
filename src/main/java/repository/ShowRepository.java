package repository;

import jakarta.persistence.EntityManager;
import model.Show;

public class ShowRepository extends Repository<Show> {

    private EntityManager em;

    ShowRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Show getById(int Id) {
        return em.find(Show.class, Id);
    }
}
