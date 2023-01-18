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
public class Movie {

    public Movie(UUID movie_id, @NonNull String title, @NonNull String genre, @NonNull String director) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    private UUID movie_id;

    @NonNull
    @PartitionKey
    @CqlName("title")
    private String title;

    @NonNull
    @PartitionKey
    @CqlName("genre")
    private String genre;

    @NonNull
    @PartitionKey
    @CqlName("director")
    private String director;

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
