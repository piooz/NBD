package util;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class CassandraConstants {

    public static final CqlIdentifier CINEMA = CqlIdentifier.fromCql("cinema");

    public static final CqlIdentifier MOVIES = CqlIdentifier.fromCql("movies");
    public static final CqlIdentifier MOVIE_ID = CqlIdentifier.fromCql("movie_id");
    public static final CqlIdentifier TITLE = CqlIdentifier.fromCql("title");
    public static final CqlIdentifier GENRE = CqlIdentifier.fromCql("genre");
    public static final CqlIdentifier DIRECTOR = CqlIdentifier.fromCql("director");

    public static final CqlIdentifier SHOWS = CqlIdentifier.fromCql("shows");
    public static final CqlIdentifier SHOW_ID = CqlIdentifier.fromCql("show_id");
    public static final CqlIdentifier SEATS = CqlIdentifier.fromCql("seats");
    public static final CqlIdentifier AVAILABLESEATS = CqlIdentifier.fromCql("availableSeats");
    public static final CqlIdentifier MOVIE = CqlIdentifier.fromCql("movie");

    public static final CqlIdentifier CLIENTS = CqlIdentifier.fromCql("clients");
    public static final CqlIdentifier CLIENT_ID = CqlIdentifier.fromCql("client_id");
    public static final CqlIdentifier LASTNAME = CqlIdentifier.fromCql("lastName");
    public static final CqlIdentifier EMAIL = CqlIdentifier.fromCql("email");


    public static final CqlIdentifier TICKETS = CqlIdentifier.fromCql("tickets");
    public static final CqlIdentifier TICKET_ID = CqlIdentifier.fromCql("ticket_id");
    public static final CqlIdentifier SEAT = CqlIdentifier.fromCql("seat");
    public static final CqlIdentifier SHOW = CqlIdentifier.fromCql("show");
    public static final CqlIdentifier CLIENT = CqlIdentifier.fromCql("client");
    public static final CqlIdentifier TYPE_INFO = CqlIdentifier.fromCql("typeInfo");



}