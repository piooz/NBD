package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import model.Show;

import java.util.List;

public class ShowRepository extends Repository<Show> {

    public ShowRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Show> findAll() {
        List<Show> list;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Show> query = cb.createQuery(Show.class);
        From<Show,Show> from = query.from(Show.class);
        query.select(from);
        list = em.createQuery(query).getResultList();
        return list;
    }
    public Show getById(long Id) {
        return em.find(Show.class, Id);
    }
}
