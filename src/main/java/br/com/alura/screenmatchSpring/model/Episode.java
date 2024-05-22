package br.com.alura.screenmatchSpring.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private Integer season;
    private String title;
    private Integer numberEp;
    private Double rating;
    private LocalDate released;

    @Override
    public String toString() {
        return "Episode{" +
                "season=" + season +
                ", title='" + title + '\'' +
                ", numberEp=" + numberEp +
                ", rating=" + rating +
                ", released=" + released +
                '}';
    }

    public Episode(Integer numberSeason, DataEpisode d) {
        this.season = numberSeason;
        this.title = d.title();
        this.numberEp = d.numberEp();
        try {
            this.rating = Double.valueOf(d.imdbRating());
        }catch (NumberFormatException e){
            this.rating = 0.0;
        }
        try {
            this.released = LocalDate.parse(d.released());
        }catch(DateTimeParseException e){
            this.released = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEp() {
        return numberEp;
    }

    public void setNumberEp(Integer numberEp) {
        this.numberEp = numberEp;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }
}
