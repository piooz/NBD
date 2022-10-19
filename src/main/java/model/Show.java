package model;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import model.Movie;
import jakarta.validation.constraints.NotNull;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Show {

    @Id
    @GeneratedValue
    private long Id;
    private int hallnumber;
    private int seats;
    private int availableSeats;



    @OneToMany
    @JoinColumn (name= "movie_id")
    private Movie movie;
    public Show(){

    }

    public Show(long id, int hallnumber, int seats, int availableSeats, Movie movie) {
        Id = id;
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
}
