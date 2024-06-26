package br.com.alura.screenmatchSpring.controller;

import br.com.alura.screenmatchSpring.dto.EpisodeDto;
import br.com.alura.screenmatchSpring.dto.SerieDto;
import br.com.alura.screenmatchSpring.model.Category;
import br.com.alura.screenmatchSpring.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public List<SerieDto> listAllSeries(){
        return serieService.listAllSerie();
    }

    @GetMapping("/top5")
    public List<SerieDto> listTopFive(){
        return serieService.listTopFive();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> getRelesead(){
        return serieService.getRelesead();
    }

    @GetMapping("/{id}")
    public SerieDto getSerieById(@PathVariable Long id){
        return serieService.getSerieById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDto> getAllSeason(@PathVariable Long id){
        return serieService.getAllSeason(id);
    }

    @GetMapping("/{id}/temporadas/{number}")
    public List<EpisodeDto> getSeasonByNumber(@PathVariable Long id, @PathVariable Long number){
        return serieService.getSeasonByNumber(id, number);
    }

    @GetMapping("/categoria/{categoria}")
    public List<SerieDto> getSerieByCategory(@PathVariable String category){
        return serieService.getSerieByCategory(category);
    }
}
