package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.dto.CountryDTO;
import br.com.cinq.spring.data.sample.application.services.CityService;
import br.com.cinq.spring.data.sample.application.services.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CityResource represents the endpoints avaiable through the api.
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@RestController
@RequestMapping(value="/rest")
@Api(value = "CityResource")
public class CityResource extends ResourceSupport {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;

	@ApiOperation(value = "Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.")
	@GetMapping(value="/cities/{id}")
	public ResponseEntity<Resource<CityDTO>> retrieveCityById(@PathVariable Integer id) {
		
		Link selfRelLink = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(CityResource.class)
				.retrieveCityById(id))
				.withSelfRel();
		CityDTO city = cityService.findCityById(id);
		Resource<CityDTO> resource = new Resource<>(city);
		resource.add(selfRelLink);
		return ResponseEntity.ok().body(resource);
	}
	
	@ApiOperation(value = "Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.")
	@GetMapping(value="/countries/{id}")
	public ResponseEntity<Resource<CountryDTO>> retrieveCountryById(@PathVariable Integer id) {
		
		Link selfRelLink = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(CityResource.class)
				.retrieveCityById(id))
				.withSelfRel();
		CountryDTO country = countryService.findCountryById(id);
		Resource<CountryDTO> resource = new Resource<>(country);
		resource.add(selfRelLink);
		return ResponseEntity.ok().body(resource);
	}
	
	@ApiOperation(value = "Returns either list of all countries (using /cities) or filtered countries (using /cities?country=<value>).")
	@GetMapping(value="/cities")
	public ResponseEntity<Resources<CityDTO>> retrieveCitiesByCountryName(@RequestParam(value = "country", required = false) String countryName) {

		List<CityDTO> cities = cityService.findCitiesByCountry(countryName);
		Resources<CityDTO> resources = new Resources<>(cities);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	}
	
	@ApiOperation(value = "Returns all countries.")
	@GetMapping(value="/countries")
	public ResponseEntity<Resources<CountryDTO>> retrieveAllCountries() {
		
		List<CountryDTO> countries = countryService.findCountries();
		Resources<CountryDTO> resources = new Resources<>(countries);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	    
	}
	
//	@ApiOperation(value = "Returns all countries.")
//	@GetMapping(value="/countries", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CountryDTO>> retrieveCountries() {
//
//		return ResponseEntity.ok().body(countryService.findCountries());
//	}
//
//	@ApiOperation(value = "Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.")
//	@GetMapping(value="/countries/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<CountryDTO> retrieveCountryById(@PathVariable Integer id) {
//		
//		return ResponseEntity.ok().body(countryService.findCountryById(id));
//
//	}
	
//	@GetMapping(value="/countries", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CountryDTO>> retrieveCountryByName(@RequestParam(value = "name") String name) {
//		
//		return ResponseEntity.ok().body(countryService.findLikeName(name));
//
//	}
	
	
//	@ApiOperation(value = "Returns either list of all countries or filtered countries using /cities?country=<value>.")
//	@GetMapping(value="/cities",produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CityDTO>> retrieveCitiesByCountry(@RequestParam(value = "country", required = false) String country) {
//		
//		return ResponseEntity.ok().body(cityService.findCitiesByCountry(country));
//	}
//	
//	@ApiOperation(value = "Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.")
//	@GetMapping(value="/cities/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<CityDTO> retrieveCityById(@PathVariable Integer id) {
//		
//		return ResponseEntity.ok().body(cityService.findCityById(id));
//	}
}
