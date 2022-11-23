package repository;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;


public abstract class Repository<T> implements IRepository<T>{
    private final ConnectionString connectionString =
            new ConnectionString("mongodb://localhost:27017");

    private final MongoCredential mongoCredential = MongoCredential.createCredential("admin","admin","pass".toCharArray());
    private final CodecRegistry codecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
            .automatic(true)
            .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
            .build());
    private MongoClient MongoClient;
    private MongoDatabase CinemaDB;

    protected void initConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(mongoCredential)
                .applyConnectionString(connectionString)
                .codecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),codecRegistry))
                .build();
        MongoClient = MongoClients.create(settings);
        CinemaDB = MongoClient.getDatabase("cinema");
    }

    public com.mongodb.client.MongoClient getMongoClient() {
        return MongoClient;
    }

    public MongoDatabase getCinemaDB() {
        return CinemaDB;
    }
}
