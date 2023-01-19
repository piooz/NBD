package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import model.Client;

import java.util.UUID;

@Dao
public interface ClientDao {
    @Insert
    void createClient(Client client);

    @GetEntity
    Client readClient(ResultSet resultSet);

    @Update
    void updateClient(Client client);

    @Delete
    void deleteClient(Client client);

    @Select
    Client getById2(UUID id);

    @Query("SELECT * FROM clients WHERE client_id = :id")
    ResultSet getById(UUID id);
    @Query("SELECT * FROM clients")
    ResultSet getAll();
}
