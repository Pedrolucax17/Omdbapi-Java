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
        double rating
) {}
