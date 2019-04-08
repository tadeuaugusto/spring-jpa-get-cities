package br.com.cinq.spring.data.sample.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.exceptions.ObjectNotFoundException;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;
import br.com.cinq.spring.data.sample.application.resources.CountryResource;

/**
 * CityService represents the service class for countries.
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@Service
public class CountryService {

	@Autowired
	private CountryRepository repo;
	
	/**
	 * Returns all countries.
	 * @return
	 */
	public List<CountryResource> findCountries() {
		
		List<CountryResource> dtoList = repo.findAll()
				.stream().map(obj -> new CountryResource(obj))
				.collect(Collectors.toList());
		
		return dtoList;
	}

	/**
	 * Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.
	 * @param id
	 * @return
	 */
	public CountryResource findCountryById(Integer id) {
		
		Optional<Country> optionalCountry = repo.findById(id);
		
		return optionalCountry.map(obj -> new CountryResource(obj)).orElseThrow(() -> new ObjectNotFoundException("Country Not Found! Id: " + id + ", [" + CountryService.class.getName() + "]"));
	}
	
	public List<CountryResource> findLikeName(String country) {
		
		List<Country> countries = repo.findLikeName(country);
		
		List<CountryResource> dtoList = countries.stream()
				.map(obj -> new CountryResource(obj))
				.distinct()
				.collect(Collectors.toList());

		return dtoList;
	}
}
