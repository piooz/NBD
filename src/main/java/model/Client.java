package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
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
public class Client {

    public Client(UUID client_id, @NonNull String lastName, @NonNull String email) {
        this.client_id = client_id;
        this.lastName = lastName;
        this.email = email;
    }

    private UUID client_id;

    @NonNull
    @PartitionKey
    @CqlName("lastName")
    private String lastName;

    @NonNull
    @PartitionKey
    @CqlName("email")
    private String email;

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
