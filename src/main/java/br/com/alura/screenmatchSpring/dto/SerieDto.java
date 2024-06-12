package br.com.alura.screenmatchSpring.dto;

import br.com.alura.screenmatchSpring.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDto(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        Category genre,
        String actors,
        String poster,
        String plot
) {
}
