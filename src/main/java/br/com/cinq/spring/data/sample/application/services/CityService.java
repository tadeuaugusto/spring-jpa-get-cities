package br.com.cinq.spring.data.sample.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.exceptions.ObjectNotFoundException;
import br.com.cinq.spring.data.sample.application.repositories.CityRepository;

/**
 * CityService represents the service class for cities.
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	/**
	 * Returns either list of all cities or filtered cities using /cities?country=<value>.
	 * @param country
	 * @return
	 */
	public List<CityDTO> findCitiesByCountry(String country) {
		
		Optional<String> optionalParam = Optional.ofNullable(country);
		List<City> cities = !optionalParam.isPresent() 
							? repo.findAll() 
							: repo.findByCountry(country);
		
		List<CityDTO> dtoList = cities.stream()
				.map(obj -> new CityDTO(obj))
				.distinct()
				.collect(Collectors.toList());

		return dtoList;
	}

	/**
	 * Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.
	 * @param id
	 * @return
	 */
	public CityDTO findCityById(Integer id) {

		return repo.findById(id)
				.map(obj -> new CityDTO(obj))
				.orElseThrow(() -> new ObjectNotFoundException("City Not Found! [" + CityService.class.getName() + "]"));
	}

}
