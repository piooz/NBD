package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Client {

    @Id
    @GeneratedValue
    private long Id;
    private String fistName;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

}
