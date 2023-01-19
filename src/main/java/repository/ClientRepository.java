package repository;



import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.ClientDao;
import dao.ShowDao;
import mappers.ClientMapper;
import mappers.ClientMapperBuilder;
import mappers.ShowMapper;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ClientRepository implements IRepository<Client> {

    protected CqlSession session;
    protected ClientMapper clientMapper;
    protected ClientDao clientDao;

    public ClientRepository(CqlSession session) {
        this.session = session;
        clientMapper = new ClientMapperBuilder(session).build();
        clientDao = clientMapper.clientDao();
    }
    @Override
    public Client get(UUID id) {
        return clientDao.readClient(clientDao.getById(id));
    }

    @Override
    public void add(Client elements) {
        clientDao.createClient(elements);
    }

    @Override
    public void remove(Client elements) {
        clientDao.deleteClient(elements);
    }

    @Override
    public void update(Client elements) {
        clientDao.updateClient(elements);
    }

    @Override
    public List<Client> find(Object elements) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
