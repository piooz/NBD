package model;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import model.Movie;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Show implements Serializable {

    @Id
    @GeneratedValue
    private long Id;
    private int hallnumber;
    private int seats;
    private int availableSeats;

    @Version
    private long version;



    @ManyToOne
    @JoinColumn (name= "movie_id")
    private Movie movie;
    public Show(){
    }

    public Show(int hallnumber, int seats, int availableSeats, Movie movie) {
        this.hallnumber = hallnumber;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Show{" +
                "Id=" + Id +
                ", hallnumber=" + hallnumber +
                ", seats=" + seats +
                ", availableSeats=" + availableSeats +
                ", movie=" + movie +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getHallnumber() {
        return hallnumber;
    }

    public void setHallnumber(int hallnumber) {
        this.hallnumber = hallnumber;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
