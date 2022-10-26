package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import model.Movie;
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
        EntityTransaction et = em.getTransaction();
        et.begin();
        mr.remove(mr.getById(Id));
        et.commit();
    }

    public List<Movie> findAll() {

        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Movie> movies = mr.findAll();
        et.commit();
        return movies;
    }
    public Movie getMovie(long Id) {

        EntityTransaction et = em.getTransaction();
        et.begin();
        Movie mv = mr.getById(Id);
        et.commit();
        return mv;
    }

    public List<Movie> findMovie(CriteriaQuery<Movie> query) {

        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Movie> list = mr.findByQuery(query);
        et.commit();
        return list;
    }

    public Movie addMovie(String title, int duration, String genre, String director) {
            Movie movie = new Movie(title,duration,genre,director);

            EntityTransaction et = em.getTransaction();
            et.begin();
            mr.add(movie);
            et.commit();
            return movie;
    }
}
