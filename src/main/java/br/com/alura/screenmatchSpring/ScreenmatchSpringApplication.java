package br.com.alura.screenmatchSpring;

import br.com.alura.screenmatchSpring.model.DataEpisode;
import br.com.alura.screenmatchSpring.model.DataSeason;
import br.com.alura.screenmatchSpring.model.DataSeries;
import br.com.alura.screenmatchSpring.service.ConsumeAPI;
import br.com.alura.screenmatchSpring.service.ConvertsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String url = "https://www.omdbapi.com/?t=gilmore+girls&apikey=1e8a0a02";
		ConvertsData convert = new ConvertsData();

		String json = ConsumeAPI.getData(url);

		DataSeries series = convert.getDatas(json, DataSeries.class);

		System.out.println(series);

		url = "https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=1e8a0a02";
		json = ConsumeAPI.getData(url);
		DataEpisode episode = convert.getDatas(json, DataEpisode.class);
		System.out.println(episode);


		List<DataSeason> seasons = new ArrayList<>();

		for(int i = 1; i <= series.totalSeasons(); i++){
			url = "https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=1e8a0a02";
			json = ConsumeAPI.getData(url);
			DataSeason season = convert.getDatas(json, DataSeason.class);
			seasons.add(season);
		}

		seasons.forEach(System.out::println);

	}
}
