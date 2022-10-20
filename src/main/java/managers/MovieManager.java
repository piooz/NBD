package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Movie;
import repository.ClientRepository;
import repository.MovieRepository;

import java.util.List;

public class MovieManager {
    private final MovieRepository mr;
    private EntityManager em;

    public MovieManager(EntityManager entityManager) {
        em = entityManager;
        mr = new MovieRepository(entityManager);
    }

    public void removeMovie(long Id) {
        mr.remove(mr.getById(Id));
    }

    public List<Movie> findAll() {
        return mr.findAll();
    }
    public Movie getMovie(long Id) {
        return mr.getById(Id);
    }

    public List<Movie> findMovie(CriteriaQuery<Movie> query) {
        return mr.findByQuery(query);
    }

    public Movie addMovie(String title, int duration, String genre, String director) {
            Movie movie = new Movie(title,duration,genre,director);
            mr.add(movie);
            return movie;
    }
}
