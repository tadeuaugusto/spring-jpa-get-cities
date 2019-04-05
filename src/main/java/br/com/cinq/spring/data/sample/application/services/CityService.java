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

@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	/**
	 * Returns either list of all countries or filtered countries using /cities?country=<value>.
	 * @param country
	 * @return
	 */
	public List<CityDTO> findByCountry(String country) {
		
		Optional<String> optionalParam = Optional.ofNullable(country);
		System.out.println(optionalParam.isPresent());
		List<City> cities = !optionalParam.isPresent() ? repo.findAll() : repo.findByCountry(country);
		
		List<CityDTO> dtoList = cities.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());

		return dtoList;
	}

	/**
	 * Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.
	 * @param id
	 * @return
	 */
	public CityDTO findById(Integer id) {

		Optional<City> optionalCity = repo.findById(id);
		
		return optionalCity.map(obj -> new CityDTO(obj)).orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", [" + CityService.class.getName() + "]"));
	}

}
