package br.com.alura.screenmatchSpring.service;

import br.com.alura.screenmatchSpring.dto.EpisodeDto;
import br.com.alura.screenmatchSpring.dto.SerieDto;
import br.com.alura.screenmatchSpring.model.Category;
import br.com.alura.screenmatchSpring.model.Serie;
import br.com.alura.screenmatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDto> listAllSerie(){
        return convertData(serieRepository.findAll());
    }

    public List<SerieDto> listTopFive() {
        return convertData(serieRepository.findTop5ByOrderByRatingDesc());
    }

    public List<SerieDto> getRelesead(){
        return convertData(serieRepository.searchLatestEpisode());
    }

    private List<SerieDto> convertData(List<Serie> series){
        return series
                .stream()
                .map(
                        s -> new SerieDto(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot())
                )
                .collect(Collectors.toList());
    }

    public SerieDto getSerieById(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (serieOptional.isPresent()){
            Serie s  = serieOptional.get();
            return new SerieDto(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
        }
        return null;
    }

    public List<EpisodeDto> getAllSeason(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (serieOptional.isPresent()){
            Serie s  = serieOptional.get();
            return s.getEpisodes()
                    .stream()
                    .map(e -> new EpisodeDto(e.getSeason(), e.getTitle(), e.getNumberEp()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodeDto> getSeasonByNumber(Long id, Long number) {
        return serieRepository.getEpisodeBySeason(id, number)
                .stream()
                .map(e -> new EpisodeDto(e.getSeason(), e.getTitle(), e.getNumberEp()))
                .collect(Collectors.toList());
    }

    public List<SerieDto> getSerieByCategory(String genre) {
        Category category = Category.fromPortugues(genre);
        return convertData(serieRepository.findByGenre(category));
    }
}
