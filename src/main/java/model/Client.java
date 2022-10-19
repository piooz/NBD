package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Client {
    @Id
    @GeneratedValue
    private int Id;
    private String firstName;
    @Email
    private String email;
    public Client() { }

    public Client(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
