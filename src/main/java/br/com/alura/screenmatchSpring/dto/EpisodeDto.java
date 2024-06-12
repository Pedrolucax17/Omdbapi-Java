package br.com.alura.screenmatchSpring.dto;

import br.com.alura.screenmatchSpring.model.Serie;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record EpisodeDto(
        Integer season,
        String title,
        Integer numberEp
) {
}
