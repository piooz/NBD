package model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Reduced")
@Access(AccessType.FIELD)
public class Reduced extends Ticket{
    public Reduced(int seatNumber, float price, Client client, Show show) {
        super(seatNumber, price, client, show);
    }

    @Override
    public float applyDiscount(float price){
        return (float) (0.7*price);
    }
}
