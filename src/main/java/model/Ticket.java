package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "type")
@Access(AccessType.FIELD)
public abstract class Ticket implements Serializable {

    public Ticket() {
    }

    public Ticket(int seatNumber, float price, Client client, Show show) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.client = client;
        this.show = show;
    }

    @Id
    @GeneratedValue
    @Cascade({CascadeType.ALL})
    private long id;
    private int seatNumber;
    private float price;
    @ManyToOne
    @JoinColumn(name = "client_fk")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "show_fk")
    private Show show;

    public abstract float applyDiscount(float price);

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", client=" + client +
                ", show=" + show +
                '}';
    }
}


