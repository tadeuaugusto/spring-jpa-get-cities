package br.com.cinq.spring.data.sample.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.dto.CountryDTO;
import br.com.cinq.spring.data.sample.application.exceptions.ObjectNotFoundException;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository repo;
	
	/**
	 * Returns all countries.
	 * @return
	 */
	public List<CountryDTO> findAll() {
		
		List<CountryDTO> dtoList = repo.findAll()
				.stream().map(obj -> new CountryDTO(obj))
				.collect(Collectors.toList());
		
		return dtoList;
	}

	/**
	 * Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.
	 * @param id
	 * @return
	 */
	public CountryDTO findById(Integer id) {
		
		Optional<Country> optionalCountry = repo.findById(id);
		
		return optionalCountry.map(obj -> new CountryDTO(obj)).orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", [" + CountryService.class.getName() + "]"));
	}
}
