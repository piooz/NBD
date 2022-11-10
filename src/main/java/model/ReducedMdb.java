package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@BsonDiscriminator(key = "_cls", value = "reduced")
public class ReducedMdb extends TicketMdb{

    @BsonCreator
    public ReducedMdb(@BsonId ObjectId id,
                      @BsonProperty("price") float price,
                      @BsonProperty("seatNumber") int seatNumber,
                      @BsonProperty("show") ObjectId show,
                      @BsonProperty("client") ObjectId client ) {
        super(id, price, seatNumber, show, client);
    }

    public float applyDiscount() {
        return (float) (getPrice() * 0.8);
    }
}
