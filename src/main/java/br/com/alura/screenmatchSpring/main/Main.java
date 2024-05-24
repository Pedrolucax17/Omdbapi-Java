package br.com.alura.screenmatchSpring.main;

import br.com.alura.screenmatchSpring.model.DataEpisode;
import br.com.alura.screenmatchSpring.model.DataSeason;
import br.com.alura.screenmatchSpring.model.DataSeries;
import br.com.alura.screenmatchSpring.model.Episode;
import br.com.alura.screenmatchSpring.service.ConsumeAPI;
import br.com.alura.screenmatchSpring.service.ConvertsData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

//        List<DataEpisode> dataEpisodes = seasons
//                .stream()
//                .flatMap(t -> t.episodes().stream())
//                .collect(Collectors.toList());
//
//
//        System.out.println("Top 5 Episódios");
//
//        dataEpisodes
//                .stream()
//                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DataEpisode::imdbRating).reversed())
//                .limit(5)
//                .forEach(System.out::println);
//
        List<Episode> episodes = seasons.stream()
                .flatMap(t -> t.episodes().stream()
                        .map(d -> new Episode(t.numberSeason(), d)))
                .collect(Collectors.toList());

        episodes.forEach(System.out::println);

        System.out.println("Digite um trecho do título do episódio");

        String title = sc.nextLine();


        Optional<Episode> searchEpisode = episodes
                .stream()
                .filter(e -> e.getTitle().toUpperCase().contains(title.toUpperCase()))
                .findFirst();
        if (searchEpisode.isPresent()){
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + searchEpisode.get().getSeason());
        }else{
            System.out.println("Episódio não encontrado!");
        }

//        System.out.println("A partir de que ano você deseja ver os episódios?");
//        int year = sc.nextInt();
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate searchDate = LocalDate.of(year, 1, 1);
//
//        episodes.stream()
//                .filter(e -> e.getReleased() != null && e.getReleased().isAfter(searchDate))
//                .forEach(e -> System.out.println(
//                    "Temporada " + e.getSeason() +
//                    " Episódio " + e.getNumberEp() +
//                    " Data Lançamento " + e.getReleased().format(dtf)
//                ));


    }
}
