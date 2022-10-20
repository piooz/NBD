package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import model.Movie;
import model.Show;
import repository.ShowRepository;
import repository.ShowRepository;

import java.util.List;

public class ShowManager {

    private final ShowRepository sr;
    private EntityManager em;

    public ShowManager(EntityManager entityManager) {
        em = entityManager;
        sr = new ShowRepository(entityManager);
    }

    public void removeShow(long Id) {
        sr.remove(sr.getById(Id));
    }

    public List<Show> findAll() {
        return sr.findAll();
    }
    public Show getShow(long Id) {
        return sr.getById(Id);
    }

    public List<Show> findShow(CriteriaQuery<Show> query) {
        return sr.findByQuery(query);
    }

    public Show addShow(int hallNumber, int seats, int availableSeats, Movie movie) {
        Show show = new Show(hallNumber, seats, availableSeats, movie);
        sr.add(show);
        return show;
    }
}
