package model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Normal")
@Access(AccessType.FIELD)
public class Normal extends Ticket{
    public Normal(int seatNumber, float price, Client client, Show show) {
        super(seatNumber, price, client, show);
    }

    @Override
    public float applyDiscount(float price) {
        return price;
    }

}
