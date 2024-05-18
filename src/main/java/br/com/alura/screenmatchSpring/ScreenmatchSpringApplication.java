package br.com.alura.screenmatchSpring;

import br.com.alura.screenmatchSpring.main.Main;
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
	public void run(String... args){
		Main main = new Main();
		main.showMenu();

	}
}
