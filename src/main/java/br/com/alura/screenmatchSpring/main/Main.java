package br.com.alura.screenmatchSpring.main;

import br.com.alura.screenmatchSpring.model.*;
import br.com.alura.screenmatchSpring.repository.SerieRepository;
import br.com.alura.screenmatchSpring.service.ConsumeAPI;
import br.com.alura.screenmatchSpring.service.ConvertsData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
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
    private List<Serie> series = new ArrayList<>();

    private SerieRepository serieRepository;

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            String menu =
                    "1 - Buscar Séries \n"
                            + "2 - Buscar episódios \n"
                            + "3 - Listar séries buscadas \n"
                            + "4 - Buscar série por título \n"
                            + "5 - Buscar série por autor \n"
                            + "6 - Top 5 séries \n"
                            + "0 - Sair";

            System.out.println(menu);
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    searchSerieWeb();
                    break;
                case 2:
                    searchEpisodeSerie();
                    break;
                case 3:
                    listSeries();
                    break;
                case 4:
                    listSeriesByTitle();
                    break;
                case 5:
                    listSeriesByActor();
                    break;
                case 6:
                    listTop5Series();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void searchSerieWeb() {
        DataSeries dataSerie = getDataSerie();
        Serie serie = new Serie(dataSerie);
        serieRepository.save(serie);
        System.out.println(dataSerie);
    }

    private DataSeries getDataSerie() {
        System.out.println("Digite o nome da série para busca");
        String name = sc.nextLine();
        String json = ConsumeAPI.getData(BASE_URL + name.replace(" ", "+") + API_KEY);
        DataSeries data = convert.getDatas(json, DataSeries.class);
        return data;
    }

    private void searchEpisodeSerie() {
        listSeries();
        System.out.println("Escolha uma série pelo nome");
        String serie = sc.nextLine();

        Optional<Serie> serieOptional = serieRepository.findByTitleContainingIgnoreCase(serie);

        if (serieOptional.isPresent()) {
            Serie serieFound = serieOptional.get();
            List<DataSeason> seasons = new ArrayList<>();

            for (int i = 1; i <= serieFound.getTotalSeasons(); i++) {
                String json = ConsumeAPI.getData(BASE_URL + serieFound.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
                DataSeason dataSeason = convert.getDatas(json, DataSeason.class);
                seasons.add(dataSeason);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons
                    .stream()
                    .flatMap(
                            d -> d.episodes().stream()
                                    .map(e -> new Episode(d.numberSeason(), e)))
                    .collect(Collectors.toList());
            serieFound.setEpisodes(episodes);
            serieRepository.save(serieFound);
        } else {
            System.out.println("Série não encontrada");
        }

    }

    private void listSeries() {
        series = serieRepository.findAll();
        series
                .stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);

    }

    private void listSeriesByTitle() {
        System.out.print("Digite o titulo da série: ");
        String title = sc.nextLine();
        Optional<Serie> serieOptional = serieRepository.findByTitleContainingIgnoreCase(title);
        if (serieOptional.isPresent()){
            System.out.println("Dados da série " + serieOptional.get());
        }else{
            System.out.println("Série não encontrada");
        }
    }

    private void listSeriesByActor(){
        System.out.println("Digite o nome do autor");
        String nameActor = sc.nextLine();
        List<Serie> serieList = serieRepository.findByActorsContainingIgnoreCase(nameActor);
        System.out.println("Série em que " + nameActor + " trabalhou");
        serieList.forEach(s -> System.out.println(s.getTitle() + " Avaliação " + s.getRating()));
    }

    private void listTop5Series(){
        List<Serie> favoritesSeries = serieRepository.findTop5ByOrderByRatingDesc();
        favoritesSeries.forEach(s -> System.out.println(s.getTitle() + " Avaliação " + s.getRating()));
    }
}
