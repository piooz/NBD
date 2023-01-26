package Utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.google.gson.Gson;
import model.TicketMdb;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaTicketProducer {
    private String topicName = "tickets";
    Gson mapper = new Gson();
    public void createTopic() throws InterruptedException {
        Properties properties = new Properties();

        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        int partitionsNumber = 3;
        short replicationFactor = 3;

        try (Admin admin = Admin.create(properties)) {
            NewTopic newTopic = new NewTopic(topicName, partitionsNumber, replicationFactor);
            CreateTopicsOptions options = new CreateTopicsOptions()
                    .timeoutMs(1000)
                    .validateOnly(false)
                    .retryOnQuotaViolation(true);
            CreateTopicsResult result = admin.createTopics(List.of(newTopic), options);
            KafkaFuture<Void> futureResult = result.values().get(topicName);
            futureResult.get();
        } catch (ExecutionException exception) {
            // jeżeli już isnieje ignoruj
            System.out.println(exception);
        }
    }

    public void produceTicket(TicketMdb ticket) {
        Properties producerConfig = new Properties();

        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG, "local");
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
        producerConfig.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        String mappedTicket = mapper.toJson(ticket, TicketMdb.class);

        KafkaProducer producer = new KafkaProducer(producerConfig);
        try {
            createTopic();
            Random random = new Random();
            int randomInt = random.nextInt(4) + 1;
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName,
                    "Cinema" + randomInt, mappedTicket);
            Future<RecordMetadata> sent = producer.send(record);
            sent.get();
        } catch (InterruptedException | ExecutionException exception) {
            System.out.println(exception);
        }
    }
}
