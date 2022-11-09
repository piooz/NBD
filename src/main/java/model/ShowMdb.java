package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import repository.MovieRepository;

import java.util.Objects;

public class ShowMdb {
    @BsonProperty("_id")
    private ObjectId id;
    @BsonProperty("seats")
    private int seats;
    @BsonProperty("availableSeats")
    private int availableSeats;
    @BsonProperty("hallNumber")
    private int hallNumber;
    @BsonProperty("movie")
    private MovieMdb movie;

    @BsonCreator
    public ShowMdb(@BsonProperty("_id") ObjectId id,
                   @BsonProperty("seats") int seats,
                   @BsonProperty("availableSeats") int availableSeats,
                   @BsonProperty("hallNumber") int hallNumber,
                   @BsonProperty("movie") MovieMdb movie) {
        this.id = id;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.hallNumber = hallNumber;
        this.movie = movie;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public MovieMdb getMovieMdb() {
        return movie;
    }

    public void setMovieMdb(MovieMdb movieMdb) {
        this.movie = movieMdb;
    }

    @Override
    public String toString() {
        return "ShowMdb{" +
                "id=" + id +
                ", seats=" + seats +
                ", availableSeats=" + availableSeats +
                ", hallNumber=" + hallNumber +
                ", movie=" + movie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowMdb showMdb = (ShowMdb) o;
        return seats == showMdb.seats && availableSeats == showMdb.availableSeats && hallNumber == showMdb.hallNumber && id.equals(showMdb.id) && Objects.equals(movie, showMdb.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seats, availableSeats, hallNumber, movie);
    }
}