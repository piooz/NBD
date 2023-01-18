package mappers;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import dao.MovieDao;

@Mapper
public interface MovieMapper {
    @DaoFactory
    MovieDao movieDao(@DaoKeyspace String keyspace, @DaoTable String table);

    @DaoFactory
    MovieDao movieDao();
}
