package repository;


import com.datastax.oss.driver.api.core.CqlSession;
import dao.ShowDao;
import mappers.MovieMapperBuilder;
import mappers.ShowMapper;
import mappers.ShowMapperBuilder;
import model.Show;

import java.util.List;
import java.util.UUID;

public class ShowRepository extends Repository<Show> {

    protected CqlSession session;
    protected ShowMapper showMapper;
    protected ShowDao showDao;

    ShowRepository(CqlSession session) {
        this.session = session;
        showMapper = new ShowMapperBuilder(session).build();
        showDao = showMapper.showDao();
    }

    @Override
    public Show get(UUID id) {
        return showDao.readShow(showDao.getById(id));
    }

    @Override
    public void add(Show elements) {
        showDao.createShow(elements);
    }

    @Override
    public void remove(Show elements) {
        showDao.deleteShow(elements);
    }

    @Override
    public void update(Show elements) {
        showDao.updateShow(elements);
    }

    @Override
    public List<Show> find(Object elements) {
        return null;
    }

    @Override
    public List<Show> getAll() {
        return null;
    }
}
