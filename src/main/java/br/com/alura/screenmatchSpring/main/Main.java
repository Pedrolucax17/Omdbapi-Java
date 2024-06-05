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
    private List<DataSeries> dataSeries = new ArrayList<>();

    public void showMenu(){
        int option = -1;
        while(option != 0){
            String menu =
                      "1 - Buscar Séries \n"
                    + "2 - Buscar episódios \n"
                    + "3 - Listar séries buscadas \n"
                    + "0 - Sair";

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    searchSerieWeb();
                    break;
                case 2:
                    searchEpisodeSerie();
                    break;
                case 3:
                    listSeries();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchSerieWeb(){
        DataSeries series = getDataSerie();
        dataSeries.add(series);
        System.out.println(series);
    }

    private DataSeries getDataSerie(){
        System.out.println("Digite o nome da série para busca");
        String name = sc.nextLine();
        String json = ConsumeAPI.getData(BASE_URL + name.replace(" ", "+") + API_KEY);
        DataSeries data = convert.getDatas(json, DataSeries.class);
        return data;
    }

    private void searchEpisodeSerie(){
        DataSeries dataSeries = getDataSerie();
        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i<= dataSeries.totalSeasons(); i++){
            String json = ConsumeAPI.getData(BASE_URL + dataSeries.title().replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeason dataSeason = convert.getDatas(json, DataSeason.class);
            seasons.add(dataSeason);
        }

        seasons.forEach(System.out::println);

    }

    private void listSeries(){
        dataSeries.forEach(System.out::println);
    }
}
