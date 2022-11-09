package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@BsonDiscriminator(key = "_cls", value = "normal")
public class NormalMdb extends TicketMdb {

    @BsonCreator
    public NormalMdb(@BsonId ObjectId id,
                      @BsonProperty("price") float price,
                      @BsonProperty("seatNumber") int seatNumber,
                      @BsonProperty("show") ObjectId show) {
        super(id, price, seatNumber, show);
    }

    public float applyDiscount() {
        return 1 * getPrice();
    }
}
