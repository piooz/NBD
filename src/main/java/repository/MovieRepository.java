package repository;

import jakarta.persistence.EntityManager;
import model.Movie;

public class MovieRepository extends Repository<Movie> {

    private EntityManager em;

    MovieRepository(EntityManager entityManager) {
        super(entityManager);
    }


    public Movie getById(int Id) {
        return em.find(Movie.class, Id);
    }
}
