package dao;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Show;

import java.util.UUID;

@Dao
public interface ShowDao {
    @Insert
    void createShow(Show show);

    @GetEntity
    Show readShow(ResultSet resultSet);

    @Update
    void updateShow(Show show);

    @Delete
    void deleteShow(Show show);

    @Query("SELECT * FROM shows WHERE show_id = :id")
    ResultSet getById(UUID id);

    @Query("SELECT * FROM shows")
    Iterable<Object> getAll(UUID id);
}
