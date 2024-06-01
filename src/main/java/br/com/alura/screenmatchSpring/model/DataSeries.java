package br.com.alura.screenmatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries(
        @JsonAlias("Title")
        String title,
        @JsonAlias("totalSeasons")
        int totalSeasons,
        @JsonAlias("imdbRating")
        String rating,
        @JsonAlias("Genre")
        String genre,
        @JsonAlias("Actors")
        String actors,
        @JsonAlias("Plot")
        String plot,
        @JsonAlias("Poster")
        String poster

) {}
