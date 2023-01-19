package model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@CqlName("shows")
@Entity
public class Show {

    public Show() {
    }

    public Show(UUID show_id, @NonNull int seats, @NonNull int availableSeats, @NonNull UUID movie) {
        this.show_id = show_id;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.movie = movie;
    }

    @NotNull
    @PartitionKey
    @CqlName("show_id")
    private UUID show_id;


    @CqlName("seats")
    private int seats;


    @CqlName("availableSeats")
    private int availableSeats;

    @NonNull
    @CqlName("movie")
    private UUID movie;

    public UUID getShow_id() {
        return show_id;
    }

    public void setShow_id(UUID show_id) {
        this.show_id = show_id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public UUID getMovie() {
        return movie;
    }

    public void setMovie(UUID movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Show{" +
                "show_id=" + show_id +
                ", seats=" + seats +
                ", availableSeats=" + availableSeats +
                ", movie=" + movie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show)) return false;
        Show show = (Show) o;
        return seats == show.seats && availableSeats == show.availableSeats && show_id.equals(show.show_id) && movie.equals(show.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(show_id, seats, availableSeats, movie);
    }
}