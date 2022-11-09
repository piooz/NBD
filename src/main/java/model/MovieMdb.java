package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

public class MovieMdb {

//    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    @BsonProperty("title")
    private String title;
    @BsonProperty("genre")
    private String genre;
    @BsonProperty("director")
    private String director;

    @BsonCreator
    public MovieMdb(@BsonId ObjectId id,
                    @BsonProperty("title") String title,
                    @BsonProperty("genre") String genre,
                    @BsonProperty("director") String director) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    public MovieMdb(String title, String genre, String director) {
        this.id = new ObjectId();
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "MovieMdb{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieMdb movieMdb = (MovieMdb) o;
        return id.equals(movieMdb.id) && Objects.equals(title, movieMdb.title) && Objects.equals(genre, movieMdb.genre) && Objects.equals(director, movieMdb.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, director);
    }
}
