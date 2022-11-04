package model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class MovieMdb {

    @BsonProperty("_id")
    private ObjectId id;
    @BsonProperty("title")
    private String title;
    @BsonProperty("genre")
    private String genre;
    @BsonProperty("director")
    private String director;

    @BsonCreator
    public MovieMdb(@BsonProperty("_id") ObjectId id,
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
}
