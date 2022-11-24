package repository.cache;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import model.TicketMdb;
import org.bson.types.ObjectId;
import redis.clients.jedis.*;
import redis.clients.jedis.args.FlushMode;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;
import repository.IRepository;
import repository.RepositoryDecorator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisTicketCache extends RepositoryDecorator<TicketMdb> {

    private JedisClientConfig jedisClientConfig;
    private JedisPooled redisClient;
    private final Jsonb jsonb;

    public RedisTicketCache(IRepository<TicketMdb> repository) {
        super.repository = repository;
        setConnection();
        jsonb = JsonbBuilder.create();
    }
    private void setConnection() {
        this.jedisClientConfig = DefaultJedisClientConfig.builder().build();

        try {
            HostAndPort hp;
            hp = readConfig();
            this.redisClient = new JedisPooled(hp, jedisClientConfig);
            System.out.println("Hello");
        } catch (IOException e) {
            this.redisClient = new JedisPooled(new HostAndPort("localhost", 6379), jedisClientConfig);
        }

    }

    private HostAndPort readConfig() throws IOException {
        InputStream inputStream = null;
        HostAndPort hp = null;
            try {
                Properties prop = new Properties();
                String propFileName = "config.connection";
                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

                String server = prop.getProperty("server");
                String port = prop.getProperty("port");
                int parseInt = Integer.parseInt(port);

                hp = new HostAndPort(server, parseInt);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
                inputStream.close();
            }
            return hp;
    }

    @Override
    public TicketMdb get(ObjectId id) {
        try {
            TicketMdb ticket = jsonb.fromJson(redisClient.get("ticket:" + id.toHexString()), TicketMdb.class);
            return ticket;

        } catch (JedisException | NullPointerException e) {
            return super.repository.get(id);
        }
    }

    @Override
    public boolean add(TicketMdb item) {
        try {
            String key = "ticket:" + item.getId().toHexString();
            redisClient.jsonSet(key, jsonb.toJson(item, TicketMdb.class));
            redisClient.expire(key, 1200);
            return super.repository.add(item);
        } catch (JedisException e) {
            return super.repository.add(item);
        }
    }

    @Override
    public void update(TicketMdb item) {
        try {
            redisClient.jsonSet("ticket:" + item.getId().toHexString(), item);
            super.repository.add(item);
        } catch (JedisException e) {
            super.repository.add(item);
        }
    }

    @Override
    public TicketMdb remove(ObjectId id) {
        try {
            TicketMdb ticket;
            ticket = get(id);
            redisClient.del("ticket:" + id.toHexString());
            super.repository.remove(id);
            return ticket;
        } catch (JedisException e) {
            TicketMdb ticket;
            ticket = super.repository.remove(id);
            return ticket;
        }
    }
    public void flushCache() {
        Jedis jd = new Jedis();
        jd.flushAll();
    }
}
