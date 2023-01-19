package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Movie;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import java.util.UUID;

@Dao
public interface MovieDao {

    @Insert
    void createMovie(Movie movie);


    @GetEntity
    Movie getMovie(ResultSet resultSet);

    @Update
    void updateClient(Movie client);

    @Delete
    void deleteClient(Movie client);

    @Query("SELECT * FROM movies WHERE movie_id = :id")
    ResultSet getById(UUID id);

    @Query("SELECT * FROM movies")
    Iterable<Object> getAll(UUID id);

}
