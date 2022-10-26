package managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import model.Movie;
import model.Show;
import repository.ShowRepository;

import java.util.List;

public class ShowManager {

    private final ShowRepository sr;
    private EntityManager em;
    private EntityTransaction et;

    public ShowManager(EntityManager entityManager) {
        em = entityManager;
        sr = new ShowRepository(entityManager);
    }

    public void removeShow(long Id) {
        et = em.getTransaction();
        et.begin();
        sr.remove(sr.getById(Id));
        et.commit();
    }

    public List<Show> findAll() {
        et = em.getTransaction();
        et.begin();
        List<Show> list = sr.findAll();
        et.commit();
        return list;
    }
    public Show getShow(long Id) {
        et = em.getTransaction();
        et.begin();
        Show sh = sr.getById(Id);
        et.commit();
        return sh;
    }

    public List<Show> findShow(CriteriaQuery<Show> query) {
        et = em.getTransaction();
        et.begin();
        List<Show> list = sr.findByQuery(query);
        et.commit();
        return list;
    }

    public Show addShow(int hallNumber, int seats, int availableSeats, Movie movie) {
        Show show = new Show(hallNumber, seats, availableSeats, movie);

        et = em.getTransaction();
        et.begin();
        sr.add(show);
        et.commit();
        return show;
    }
}
