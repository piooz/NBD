package model;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;

import java.net.InetSocketAddress;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createKeyspace;
import static util.CassandraConstants.*;

public class CinemaDDL {

    private static CqlSession session;

    public static CqlSession initSession() {
        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9042))
                .addContactPoint(new InetSocketAddress("localhost", 9043))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra", "cassandrapassword")
                .withKeyspace(CqlIdentifier.fromCql("cinema"))
                .build();

        // Dlaczego Kasia nie odpowiada tak długo? / pewnie lubi mojego requesta
        // Odkomentuj, storz tabele w dokerze tylko raz i zakomentuj aby nie kusic TimeOut Exception

//        CreateKeyspace keyspace = createKeyspace(CINEMA)
//                .ifNotExists()
//                .withSimpleStrategy(2)
//                .withDurableWrites(true);
//        SimpleStatement se = keyspace.build();
//        session.execute(se);

//        SimpleStatement createClients = SchemaBuilder.createTable(CLIENTS)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("client_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("lastName"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("email"), DataTypes.TEXT)
//                .build();
//        session.execute(createClients);
//
//        SimpleStatement createMovies= SchemaBuilder.createTable(MOVIES)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("movie_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("title"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("genre"), DataTypes.TEXT)
//                .withColumn(CqlIdentifier.fromCql("director"), DataTypes.TEXT)
//                .build();
//        session.execute(createMovies);

//        SimpleStatement createShows = SchemaBuilder.createTable(SHOWS)
//                .ifNotExists()
//                .withPartitionKey(CqlIdentifier.fromCql("show_id"), DataTypes.UUID)
//                .withColumn(CqlIdentifier.fromCql("seats"), DataTypes.INT)
//                .withColumn(CqlIdentifier.fromCql("availableSeats"), DataTypes.INT)
//                .withColumn(CqlIdentifier.fromCql("movie"), DataTypes.UUID)
//                .build();
//        session.execute(createShows);

        return session;
    }
}
