package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DialectOverride;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Client {
    @Id
    @GeneratedValue
    private long Id;
    private String firstName;
    @Email
    private String email;

    @Version
    private long version;
    public Client() { }

    public Client(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public long getId() {
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
    public void setId(long id) {
        Id = id;
    }
}
