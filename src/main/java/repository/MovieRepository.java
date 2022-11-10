package repository;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.result.UpdateResult;
import model.MovieMdb;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

public class MovieRepository extends Repository{

    private final MongoCollection<MovieMdb> movieMdbCollection;

    public MovieRepository() {
        initConnection();
        try {
            getCinemaDB().createCollection("movies", new CreateCollectionOptions().validationOptions( new ValidationOptions().validator(
                    Document.parse(
                            """
    {
       $jsonSchema: {
          bsonType: "object",
          required: [ "title" ],
          properties: {
             title: {
                bsonType: "string",
                description: "must be a string"
             },
             director: {
                bsonType: "string",
                description: "must be a string"
             },
             genre: {
                bsonType: "string",
                description: "must be a string"
             }
          }
       }
    }
                    """
                    )
            )));
        } catch(MongoCommandException ignored) { }

        movieMdbCollection = getCinemaDB().getCollection("movies", MovieMdb.class);
    }

    public boolean add(MovieMdb movie) {
        if(isExisting(movie)) {
            return false;
        }
        movieMdbCollection.insertOne(movie);
        return true;
    }

    private boolean isExisting(MovieMdb movie) {
        Bson filter;
            filter = or(eq("title", movie.getTitle()), eq("_id", movie.getId()));

        ArrayList<MovieMdb> ls = movieMdbCollection.find(filter).into(new ArrayList<>());
        return !ls.isEmpty();
    }

    public MovieMdb remove(ObjectId id) {
        Bson filer = eq("_id", id);
        return movieMdbCollection.findOneAndDelete(filer);
    }
    public void drop()
    {
        movieMdbCollection.drop();
    }

    public ArrayList<MovieMdb> findAll() {
        return movieMdbCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<MovieMdb> find(ObjectId id) {
        Bson filter = eq("_id", id);

        return movieMdbCollection.find(filter, MovieMdb.class).into(new ArrayList<> ());
    }

    public MovieMdb updateOne(ObjectId id, Bson updateOperation) {
        Bson filter = eq("_id", id);
        return movieMdbCollection.findOneAndUpdate(filter, updateOperation);
    }

    public UpdateResult updateMany(Bson filter, Bson updateOperation) {
        return movieMdbCollection.updateMany(filter, updateOperation);
    }

}
