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
        clientMdbMongoCollection = getCinemaDB().getCollection("client", ClientMdb.class);

    }
    private MongoCollection<ClientMdb> clientMdbMongoCollection;

    public void add(ClientMdb client) {
        clientMdbMongoCollection.insertOne(client);
    }

    public ClientMdb remove(ObjectId id) {
        Bson filer = eq("_id", id);
        ClientMdb client = clientMdbMongoCollection.findOneAndDelete(filer);
        return client;
    }
    public void dropAllClients()
    {
        clientMdbMongoCollection.drop();
    }

    public ArrayList<ClientMdb> findAll() {
        ArrayList<ClientMdb> list = clientMdbMongoCollection.find().into(new ArrayList<> ());
        return list;
    }

    public ArrayList<ClientMdb> find(ObjectId id) {
        Bson filter = eq("_id", id);

        ArrayList<ClientMdb> list = clientMdbMongoCollection.find(filter, ClientMdb.class).into(new ArrayList<> ());
        return list;
    }

}
