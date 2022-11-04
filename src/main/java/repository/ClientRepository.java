package repository;

import com.mongodb.client.MongoCollection;
import model.ClientMdb;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class ClientRepository extends Repository{

    public ClientRepository() {
        initConnection();
        clientMdbMongoCollection = getCinemaDB().getCollection("clients", ClientMdb.class);
        System.out.println(clientMdbMongoCollection);
    }
    private final MongoCollection<ClientMdb> clientMdbMongoCollection;

    public boolean add(ClientMdb client) {
        if(isExisting(client)) {
            return false;
        }
        clientMdbMongoCollection.insertOne(client);
        return true;
    }

    private boolean isExisting(ClientMdb client) {
        Bson filter;
        filter = eq("email", client.getEmail());

        ArrayList<ClientMdb> ls = clientMdbMongoCollection.find(filter).into(new ArrayList<>());
        return !ls.isEmpty();
    }

    public ClientMdb remove(ObjectId id) {
        Bson filer = eq("_id", id);
        return clientMdbMongoCollection.findOneAndDelete(filer);
    }
    public void dropAllClients()
    {
        clientMdbMongoCollection.drop();
    }

    public ArrayList<ClientMdb> findAll() {
        return clientMdbMongoCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<ClientMdb> find(ObjectId id) {
        Bson filter = eq("_id", id);

        return clientMdbMongoCollection.find(filter, ClientMdb.class).into(new ArrayList<> ());
    }

}
