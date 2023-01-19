package model;

import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@CqlName("tickets")
public class Ticket {

    public Ticket() {
    }

    public Ticket(UUID ticket_id, UUID show, UUID client, float price, int seat, String ticketType) {
        this.ticket_id = ticket_id;
        this.show = show;
        this.client = client;
        this.price = price;
        this.seat = seat;
        this.ticketType = ticketType;
    }

    @CqlName("ticket_id")
    @PartitionKey
    private UUID ticket_id;

    @CqlName("show")
    private UUID show;

    @CqlName("client")
    private UUID client;

    @CqlName("price")
    private float price;

    @CqlName("seat")
    private int seat;

    @ToString
    public enum TicketType{
        Normal,
        Reduced;
    }

    @CqlName("ticketinfo")
    private String ticketType = TicketType.Normal.name();



    public UUID getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(UUID ticket_id) {
        this.ticket_id = ticket_id;
    }

    public UUID getShow() {
        return show;
    }

    public void setShow(UUID show) {
        this.show = show;
    }

    public UUID getClient() {
        return client;
    }

    public void setClient(UUID client) {
        this.client = client;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", show=" + show +
                ", client=" + client +
                ", price=" + price +
                ", seat=" + seat +
                ", ticketType='" + ticketType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Float.compare(ticket.price, price) == 0 && seat == ticket.seat && ticket_id.equals(ticket.ticket_id) && show.equals(ticket.show) && client.equals(ticket.client) && ticketType.equals(ticket.ticketType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, show, client, price, seat, ticketType);
    }
}
