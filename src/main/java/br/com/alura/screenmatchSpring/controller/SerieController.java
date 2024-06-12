package br.com.alura.screenmatchSpring.controller;

import br.com.alura.screenmatchSpring.model.Serie;
import br.com.alura.screenmatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public List<Serie> getSeries(){
        return serieRepository.findAll();
    }

}
