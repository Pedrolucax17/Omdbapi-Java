package br.com.alura.screenmatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.OptionalDouble;

@Entity
@Table(name = "tbl_series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(name = "total_seasons")
    private int totalSeasons;

    private double rating;

    @Enumerated(EnumType.STRING)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
