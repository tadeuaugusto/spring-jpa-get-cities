package br.com.cinq.spring.data.sample.application;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.repositories.CityRepository;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;

@SpringBootApplication
public class SpringjpaApplication implements CommandLineRunner {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Country country1 = new Country(null, "Netherlands");
		countryRepository.saveAll(Arrays.asList(country1));
	}

	/*
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub

		Country country1 = new Country(null, "Brazil");
		Country country2 = new Country(null, "Argentina");
		Country country3 = new Country(null, "Netherlands");
		Country country4 = new Country(null, "Ireland");
		Country country5 = new Country(null, "Norway");
//		Country country6 = new Country(6, "France");
//		Country country7 = new Country(7, "United States");
//		Country country8 = new Country(8, "Japan");
//		Country country9 = new Country(9, "Canada");
//		Country country10 = new Country(10, "Jamaica");
		
		City city1 = new City(null, "Andirá", country1);
		City city2 = new City(null, "Curitiba", country1);
		City city3 = new City(null, "Londrina", country1);
		City city4 = new City(null, "Buenos Aires", country2);
		City city5 = new City(null, "Amsterdam", country3);
		City city6 = new City(null, "Leewarden", country3);
		
		City city7 = new City(null, "Dublin");
		City city8 = new City(null, "Oslo");
		City city9 = new City(null, "Bergen");
		City city10 = new City(null, "Trondheim");
		
		country1.getCities().addAll(Arrays.asList(city1, city2, city3));
//		country2.getCities().addAll(Arrays.asList(city4));
//		country3.getCities().addAll(Arrays.asList(city5, city6));
//		country4.getCities().addAll(Arrays.asList(city7));
//		country5.getCities().addAll(Arrays.asList(city8, city9, city10));
		
		
		// countryRepository.saveAll(Arrays.asList(country1, country2, country3, country4, country5));
		// cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4,city5,city6,city7,city8,city9,city10));
	}
	*/

}
