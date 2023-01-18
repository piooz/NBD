package model;


import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;


import java.io.Serializable;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@CqlName("movies")
@Entity
public class Movie {
    public Movie() {
    }

    public Movie(UUID movie_id, @NonNull String title, @NonNull String genre, @NonNull String director) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    @CqlName("movie_id")
    @PartitionKey
    private UUID movie_id;


    @CqlName("title")
    private String title;


    @CqlName("genre")
    private String genre;


    @CqlName("director")
    private String director;

    public UUID getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(UUID movie_id) {
        this.movie_id = movie_id;
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return new EqualsBuilder().append(movie_id, movie.movie_id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(movie_id).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("movie_id", movie_id)
                .append("title", title)
                .append("genre", genre)
                .append("director", director)
                .toString();
    }
}
