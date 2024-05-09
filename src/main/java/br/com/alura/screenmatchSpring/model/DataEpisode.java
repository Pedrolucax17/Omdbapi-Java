package br.com.alura.screenmatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(
        @JsonAlias("Title")
        String title,
        @JsonAlias("Year")
        Integer year,
        @JsonAlias("Episode")
        Integer numberEp,
        @JsonAlias("Season")
        Integer season,
        @JsonAlias("Released")
        String released,
        @JsonAlias("imdbRating")
        double rating
) {
}
