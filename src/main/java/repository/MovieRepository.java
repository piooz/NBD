package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import model.Movie;

import java.util.List;

public class MovieRepository extends Repository<Movie> {


    public MovieRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> list;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        From<Movie,Movie> from = query.from(Movie.class);
        query.select(from);
        list = em.createQuery(query).getResultList();
        return list;
    }


    public Movie getById(long Id) {
        return em.find(Movie.class, Id);
    }
}
