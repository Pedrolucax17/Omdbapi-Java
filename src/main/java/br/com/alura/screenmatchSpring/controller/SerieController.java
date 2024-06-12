package br.com.alura.screenmatchSpring.controller;

import br.com.alura.screenmatchSpring.dto.SerieDto;
import br.com.alura.screenmatchSpring.model.Serie;
import br.com.alura.screenmatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public List<SerieDto> getSeries(){
        return serieRepository.findAll()
                .stream()
                .map(
                        s -> new SerieDto(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getGenre(), s.getActors(), s.getPlot())
                )
                .collect(Collectors.toList());
    }

}
