package model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Valid
@Access(AccessType.FIELD)
public class Movie {
    @Id
    @GeneratedValue
    private long Id;

    @NotNull
    private String title;
    private int duration;
    private String genre;
    private String director;

    public Movie(){

    }

    public Movie(String title, int duration, String genre, String director) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
