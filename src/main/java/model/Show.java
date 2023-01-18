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
@CqlName("shows")
public class Show {
    public Show(UUID show_id, @NonNull int seats, @NonNull int availableSeats, @NonNull Movie movie) {
        this.show_id = show_id;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.movie = movie;
    }

    private UUID show_id;

    @NonNull
    @PartitionKey
    @CqlName("seats")
    private int seats;

    @NonNull
    @PartitionKey
    @CqlName("availableSeats")
    private int availableSeats;

    @NonNull
    @PartitionKey
    @CqlName("movie")
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Show show = (Show) o;

        return new EqualsBuilder().append(show_id, show.show_id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(show_id).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("show_id", show_id)
                .append("seats", seats)
                .append("availableSeats", availableSeats)
                .append("movie", movie)
                .toString();
    }
}