package br.com.alura.screenmatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.OptionalDouble;

public class Serie {
    private String title;

    private int totalSeasons;

    private double rating;

    private Category genre;

    private String actors;

    private String plot;

    private String poster;

    public Serie(DataSeries dataSeries){
        this.title = dataSeries.title();
        this.totalSeasons = dataSeries.totalSeasons();
        this.rating = OptionalDouble.of(Double.parseDouble(dataSeries.rating())).orElse(0);
        this.genre = Category.fromString(dataSeries.genre().split(",")[0].trim());
        this.actors = dataSeries.actors();
        this.plot = dataSeries.plot();
        this.poster = dataSeries.poster();
    }
}
