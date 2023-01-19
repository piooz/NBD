package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.ClientDao;
import dao.MovieDao;
import mappers.ClientMapper;
import mappers.ClientMapperBuilder;
import mappers.MovieMapper;
import mappers.MovieMapperBuilder;
import model.Movie;

import java.util.List;
import java.util.UUID;

public class MovieRepository extends Repository<Movie>{

    protected CqlSession session;
    protected MovieMapper movieMapper;
    protected MovieDao movieDao;

    public MovieRepository(CqlSession session) {
        this.session = session;
        movieMapper = new MovieMapperBuilder(session).build();
        movieDao = movieMapper.movieDao();
    }

    @Override
    public Movie get(UUID id) {

        return movieDao.getMovie(movieDao.getById(id));
    }

    @Override
    public void add(Movie elements) {
        movieDao.createMovie(elements);
    }

    @Override
    public void remove(Movie elements) {
        movieDao.deleteClient(elements);
    }

    @Override
    public void update(Movie elements) {
        movieDao.updateClient(elements);
    }

    @Override
    public List<Movie> find(Object elements) {
        return null;
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }
}
