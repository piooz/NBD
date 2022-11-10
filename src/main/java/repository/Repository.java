package repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;


public abstract class Repository {
    private ConnectionString connectionString =
            new ConnectionString("mongodb://localhost:27017");

//    private ConnectionString connectionString =
//            new ConnectionString("mongodb://localhost:27017,mogo.... /?replicaSet=replica_set_single");

    private MongoCredential mongoCredential = MongoCredential.createCredential("admin","admin","pass".toCharArray());
    private CodecRegistry codecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
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

    public ConnectionString getConnectionString() {
        return connectionString;
    }

    public MongoCredential getMongoCredential() {
        return mongoCredential;
    }

    public CodecRegistry getCodecRegistry() {
        return codecRegistry;
    }

    public com.mongodb.client.MongoClient getMongoClient() {
        return MongoClient;
    }

    public MongoDatabase getCinemaDB() {
        return CinemaDB;
    }
}
