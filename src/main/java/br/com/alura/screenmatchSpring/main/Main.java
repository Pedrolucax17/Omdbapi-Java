package br.com.alura.screenmatchSpring.main;

import br.com.alura.screenmatchSpring.model.DataEpisode;
import br.com.alura.screenmatchSpring.model.DataSeason;
import br.com.alura.screenmatchSpring.model.DataSeries;
import br.com.alura.screenmatchSpring.model.Episode;
import br.com.alura.screenmatchSpring.service.ConsumeAPI;
import br.com.alura.screenmatchSpring.service.ConvertsData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private ConvertsData convert = new ConvertsData();
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=1e8a0a02";
    private Scanner sc = new Scanner(System.in);

    public void showMenu(){
        System.out.println("Digite o nome da série: ");
        String serieName = sc.nextLine();

        String json = ConsumeAPI.getData(BASE_URL + serieName.replace(" ", "+") + API_KEY);

        DataSeries series = convert.getDatas(json, DataSeries.class);

        System.out.println(series);

        List<DataSeason> seasons = new ArrayList<>();

        for(int i = 1; i <= series.totalSeasons(); i++){
            json = ConsumeAPI.getData(BASE_URL + serieName.replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeason season = convert.getDatas(json, DataSeason.class);
            seasons.add(season);
        }

        seasons.forEach(System.out::println);

        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        List<DataEpisode> dataEpisodes = seasons
                .stream()
                .flatMap(t -> t.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("Top 5 Episódios");

        dataEpisodes
                .stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}
