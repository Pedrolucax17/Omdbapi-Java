package br.com.alura.screenmatchSpring.repository;

import br.com.alura.screenmatchSpring.model.Category;
import br.com.alura.screenmatchSpring.model.Episode;
import br.com.alura.screenmatchSpring.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitleContainingIgnoreCase(String title);

    List<Serie> findByActorsContainingIgnoreCase(String nameActor);

    List<Serie> findByGenre(Category category);

    List<Serie> findTop5ByOrderByRatingDesc();

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodes e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.released) DESC LIMIT 5")
    List<Serie> searchLatestEpisode();

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s.id = :id AND e.season = :number")
    List<Episode> getEpisodeBySeason(Long id, Long number);
}
