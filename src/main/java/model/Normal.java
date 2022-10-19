package model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorValue("Normal")
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Normal extends Ticket{
    public Normal(int seatNumber, float price, Client client, Show show) {
        super(seatNumber, price, client, show);
    }

    @Override
    public float applyDiscount(float price) {
        return price;
    }

}
