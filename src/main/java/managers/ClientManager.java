package managers;

import model.ClientMdb;
import org.bson.types.ObjectId;
import repository.ClientRepository;

import java.util.List;

public class ClientManager {

    final private ClientRepository clientRepository;

    ClientManager() {
        clientRepository = new ClientRepository();
    }

    public boolean createClient(String lastName, String email) {
        clientRepository.add(new ClientMdb(new ObjectId(), lastName, email));
        return true;
    }

    public boolean removeClient(ObjectId id) {
        clientRepository.remove(id);
        return true;
    }

    public List<ClientMdb> findAll() {
        return clientRepository.findAll();
    }

}
