package lv.autoosta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.autoosta.model.BusCategory;
import lv.autoosta.model.City;
import lv.autoosta.model.Driver;
import lv.autoosta.model.Trip;
import lv.autoosta.repos.ICityRepo;
import lv.autoosta.repos.IDriverRepo;
import lv.autoosta.repos.ITripRepo;
import lv.autoosta.services.ITripService;

@SpringBootApplication
public class JavaMD2Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaMD2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner testModel(IDriverRepo drRepo, ICityRepo cityRepo, ITripRepo tripRepo, ITripService tripService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Set<BusCategory> category = Stream.of(BusCategory.minibus).collect(Collectors.toSet());
				Set<BusCategory> category2 = Stream.of(BusCategory.minibus, BusCategory.largebus).collect(Collectors.toSet());
				Driver d1 = new Driver("Cool", "Guy", category);
				Driver d2 = new Driver("Hello", "World", category2);
				drRepo.save(d1);
				drRepo.save(d2);
				
				City city1 = new City("Ventspils", "Test street 1");
				City city2 = new City("Riga", "Another street 25");
				cityRepo.save(city1);
				cityRepo.save(city2);
				
				Trip tr1 = new Trip(LocalDateTime.now(), 5, new ArrayList<>(Arrays.asList(city1)), d1);
				Trip tr2 = new Trip(LocalDateTime.now(), 7, new ArrayList<>(Arrays.asList(city2)), d2);
				Trip tr3 = new Trip(LocalDateTime.now().plusHours(1), 2, new ArrayList<>(Arrays.asList(city2)), d2);
				Trip tr4 = new Trip(LocalDateTime.now().plusHours(24), 1, new ArrayList<>(Arrays.asList(city2)), d2);
				tripRepo.save(tr1);
				tripRepo.save(tr2);
				tripRepo.save(tr3);
				tripRepo.save(tr4);
				
				tr1.addCity(city1);
				tr2.addCity(city2);
				tr2.addCity(city1);
				tr3.addCity(city1);
				tr4.addCity(city1);
				tripRepo.save(tr1);
				tripRepo.save(tr2);
				tripRepo.save(tr3);
				tripRepo.save(tr4);
			}
		};
	}
	

}
