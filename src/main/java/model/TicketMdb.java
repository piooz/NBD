package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

@BsonDiscriminator(key = "_class")
public abstract class TicketMdb {
    @BsonProperty("price")
    private float price;
    @BsonProperty("seatNumber")
    private int seatNumber;
    @BsonId
    private ObjectId id;
    @BsonProperty("show")
    private ShowMdb show;

    @BsonCreator
    public TicketMdb(@BsonId ObjectId id,
                     @BsonProperty("price") float price,
                     @BsonProperty("seatNumber") int seatNumber,
                     @BsonProperty("show") ShowMdb show ) {
        this.price = price;
        this.seatNumber = seatNumber;
        this.id = id;
        this.show = show;
    }

    public ShowMdb getShow() {
        return show;
    }

    public void setShow(ShowMdb show) {
        this.show = show;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketMdb ticketMdb = (TicketMdb) o;
        return Float.compare(ticketMdb.price, price) == 0 && seatNumber == ticketMdb.seatNumber && id.equals(ticketMdb.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, seatNumber, id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", seatNumber=" + seatNumber +
                ", id=" + id +
                '}';
    }
}