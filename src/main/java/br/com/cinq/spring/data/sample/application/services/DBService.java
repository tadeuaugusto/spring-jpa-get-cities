package br.com.cinq.spring.data.sample.application.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.repositories.CityRepository;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;

@Service
public class DBService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public void instantiateTestDB() {
		Country country1 = new Country(null, "Brazil");
		Country country2 = new Country(null, "Argentina");
		Country country3 = new Country(null, "Netherlands");
		Country country4 = new Country(null, "Ireland");
		Country country5 = new Country(null, "Norway");
		Country country6 = new Country(null, "Czech Republic");
		
		
		City city1 = new City(null, "Andirá", country1);
		City city2 = new City(null, "Curitiba", country1);
		City city3 = new City(null, "Londrina", country1);
		City city4 = new City(null, "Buenos Aires", country2);
		City city5 = new City(null, "Amsterdam", country3);
		City city6 = new City(null, "Leewarden", country3);
		
		City city7 = new City(null, "Dublin", country4);
		City city8 = new City(null, "Limerick", country4);
		City city9 = new City(null, "Galway", country4);
		City city10 = new City(null, "Cork", country4);
		City city11 = new City(null, "Oslo", country5);
		City city12 = new City(null, "Bergen", country5);
		City city13 = new City(null, "Trondheim", country5);
		City city14 = new City(null, "Prague", country6);
		
		countryRepository.saveAll(Arrays.asList(country1, country2, country3, country4, country5, country6));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4,city5,city6,city7,city8,city9,city10,city11,city12,city13,city14));
		
	}
}
