package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

@Getter
@Setter
@CqlName("clients")
@Entity
public class Client {
    public Client() {
    }

    public Client(UUID client_id, @NonNull String lastName, @NonNull String email) {
        this.client_id = client_id;
        this.lastName = lastName;
        this.email = email;
    }

    @NonNull
    @PartitionKey
    @CqlName("client_id")
    private UUID client_id;

    @NonNull
    @CqlName("lastName")
    private String lastName;

    @NonNull
    @CqlName("email")
    private String email;

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }

    public String getLastName() {
        return lastName;
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return new EqualsBuilder().append(client_id, client.client_id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(client_id).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("client_id", client_id)
                .append("lastName", lastName)
                .append("email", email)
                .toString();
    }
}
