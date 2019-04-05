package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.repositories.CityRepository;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;
import br.com.cinq.spring.data.sample.application.services.CityService;

@RestController
@RequestMapping(value = "/restv1")
public class ApiResource {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@RequestMapping(value = "/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDTO>> find(@RequestParam(value = "country", required = false) String country) {
		List<City> list = null; // country == null ? cityService.findAll() : null; // cityService.findByCountry(country);
		List<CityDTO> dtoList = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoList);
	}
	
	@RequestMapping(value="/countries", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> findAll(String country) {
		List<Country> list = countryRepository.findAll();
		return ResponseEntity.ok().body(list);
	}
}
