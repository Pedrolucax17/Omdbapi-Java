package br.com.alura.screenmatchSpring;

import br.com.alura.screenmatchSpring.model.DataSeries;
import br.com.alura.screenmatchSpring.service.ConsumeAPI;
import br.com.alura.screenmatchSpring.service.ConvertsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		System.out.println(series.title() + series.rating() + series.totalSeasons());
	}
}
