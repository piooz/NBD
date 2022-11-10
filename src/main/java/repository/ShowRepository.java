package repository;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import model.ShowMdb;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class ShowRepository extends Repository{

    private final MongoCollection<ShowMdb> showMongoCollection;

    public ShowRepository() {
        initConnection();
        try {
            getCinemaDB().createCollection("shows", new CreateCollectionOptions().validationOptions( new ValidationOptions().validator(
                    Document.parse(
                            """
    {
       $jsonSchema: {
          bsonType: "object",
          properties: {
             movie: {
                bsonType: "object",
                description: "movie object"
             },
             seats: {
                bsonType: "int",
                minimum: 1,
                description: "must be a positive integer"
             },
             availableSeats: {
                bsonType: "int",
                minimum: 0,
                description: "must be a positive integer"
             }
             hallNumber: {
                bsonType: "int",
                minimum: 0,
                description: "must be a integer"
             }
          }
       }
    }
                    """
                    )
            )));
        } catch(MongoCommandException ignored) {
        }
        showMongoCollection = getCinemaDB().getCollection("shows", ShowMdb.class);
    }
    public boolean add(ShowMdb show) {
        if(isExisting(show)) {
            return false;
        }
        showMongoCollection.insertOne(show);
        return true;
    }

    private boolean isExisting(ShowMdb show) {
        Bson filter;
        filter = eq("_id", show.getId());

        ArrayList<ShowMdb> ls = showMongoCollection.find(filter).into(new ArrayList<>());
        return !ls.isEmpty();
    }

    public ShowMdb remove(ObjectId id) {
        Bson filer = eq("_id", id);
        return showMongoCollection.findOneAndDelete(filer);
    }
    public void drop()
    {
        showMongoCollection.drop();
    }

    public ArrayList<ShowMdb> findAll() {
        return showMongoCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<ShowMdb> find(ObjectId id) {
        Bson filter = eq("_id", id);

        return showMongoCollection.find(filter, ShowMdb.class).into(new ArrayList<> ());
    }
}
