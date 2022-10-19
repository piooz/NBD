package repository;

import jakarta.persistence.EntityManager;
import model.Show;

public class ShowRepository extends Repository<Show> {

    public ShowRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Show getById(long Id) {
        return em.find(Show.class, Id);
    }
}
