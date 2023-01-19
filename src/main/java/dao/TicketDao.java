package dao;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Ticket;

import java.util.UUID;

@Dao
public interface TicketDao {

    @Insert
    void createTicket(Ticket ticket);

    @GetEntity
    Ticket readShow(ResultSet resultSet);

    @Update
    void updateTicket(Ticket ticket);

    @Delete
    void deleteShow(Ticket ticket);

    @Select
    Ticket getById(UUID id);

    @Query("SELECT * FROM tickets")
    Iterable<Object> getAll(UUID id);
}
