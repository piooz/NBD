package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import repository.MovieRepository;

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
}