package repository;



import model.Client;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ClientRepository extends Repository<Client>{


    @Override
    public Client get(Object element) {
        return null;
    }

    @Override
    public void add(Client elements) {

    }

    @Override
    public void remove(Client elements) {

    }

    @Override
    public void update(Client elements) {

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
