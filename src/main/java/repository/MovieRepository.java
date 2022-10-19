package repository;

import jakarta.persistence.EntityManager;
import model.Movie;

public class MovieRepository extends Repository<Movie> {


    public MovieRepository(EntityManager entityManager) {
        super(entityManager);
    }


    public Movie getById(long Id) {
        return em.find(Movie.class, Id);
    }
}
