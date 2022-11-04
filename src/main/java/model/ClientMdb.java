package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

public class ClientMdb {

    @BsonCreator
    public ClientMdb(@BsonProperty("_id") ObjectId clientID,
                     @BsonProperty("lastName") String lastName,
                     @BsonProperty("email") String email) {
        this.clientID = clientID;
        this.lastName = lastName;
        this.email = email;
    }

    public ClientMdb(String lastName, String email) {
        this.clientID = new ObjectId();
        this.lastName = lastName;
        this.email = email;
    }

    @BsonProperty("_id")
    private ObjectId clientID;
    @BsonProperty("lastName")
    private String lastName;
    @BsonProperty("email")
    private String email;

    public String getLastName() {
        return lastName;
    }

    public ObjectId getClientID() {
        return clientID;
    }

    public void setClientID(ObjectId clientID) {
        this.clientID = clientID;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientMdb{" +
                "clientID=" + clientID +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientMdb clientMdb = (ClientMdb) o;
        return clientID.equals(clientMdb.clientID) && Objects.equals(lastName, clientMdb.lastName) && Objects.equals(email, clientMdb.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, lastName, email);
    }
}
