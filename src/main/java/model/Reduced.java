package model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorValue("Reduced")
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Reduced extends Ticket{
    public Reduced(int seatNumber, float price, Client client, Show show) {
        super(seatNumber, price, client, show);
    }

    @Override
    public float applyDiscount(float price){
        return (float) (0.7*price);
    }
}
