package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Movie;

import com.datastax.oss.driver.api.core.cql.ResultSet;

@Dao
public interface MovieDao {

    @Insert
    void createMovie(Movie movie);

    //@QueryProvider()

    @GetEntity
    Movie getMovie(ResultSet resultSet);

    @Update
    void updateClient(Movie client);

    @Delete
    void deleteClient(Movie client);

}
