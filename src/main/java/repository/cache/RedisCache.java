package repository.cache;

import model.TicketMdb;
import org.bson.types.ObjectId;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.exceptions.JedisException;
import repository.IRepository;
import repository.RepositoryDecorator;
import repository.TicketRepository;

public class RedisCache extends RepositoryDecorator<TicketMdb> {

    protected JedisClientConfig jedisClientConfig = DefaultJedisClientConfig.builder().build();
    protected JedisPooled RedisClient;

    protected RedisCache(IRepository<TicketMdb> repository) {
        super.repository = repository;
        setConnection();
    }
    @Override
    public boolean setConnection() {
        this.jedisClientConfig = DefaultJedisClientConfig.builder().build();
        this.RedisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
        return true;
    }

    @Override
    public TicketMdb get(ObjectId id) {
        try {
            TicketMdb ticket = RedisClient.jsonGet("ticket:" + id.toHexString(), TicketMdb.class);
            if(ticket == null) {
                throw new RuntimeException();
            }
            return ticket;

        } catch (JedisException e) {
            return super.repository.get(id);
        }
    }

    @Override
    public boolean add(TicketMdb item) {
        try {
            String key = "ticket:" + item.getId();
            RedisClient.jsonSet(key, item);
            RedisClient.expire(key, 1200);
            return super.repository.add(item);
        } catch (JedisException e) {
            return super.repository.add(item);
        }
    }


    @Override
    public void update(TicketMdb item) {
        try {
            RedisClient.jsonSet("ticket:" + item.getId().toHexString(), item);
            super.repository.add(item);
        } catch (JedisException e) {
            super.repository.add(item);
        }
    }

    @Override
    public TicketMdb remove(ObjectId id) {
        try {
            TicketMdb ticket;
            ticket = RedisClient.jsonGet("ticket:" + id.toHexString(), TicketMdb.class);
            RedisClient.getDel("ticket:" + id.toHexString());
            super.repository.remove(id);
            return ticket;
        } catch (JedisException e) {
            TicketMdb ticket;
            ticket = super.repository.remove(id);
            return ticket;
        }
    }


}
