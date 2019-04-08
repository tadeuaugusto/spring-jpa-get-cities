package br.com.cinq.spring.data.sample.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cinq.spring.data.sample.application.resources.CityResource;
import br.com.cinq.spring.data.sample.application.resources.CountryResource;
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
@RequestMapping(value="/rest", produces = "application/hal+json")
@Api(value = "CityResource")
public class CityController { //extends ResourceSupport {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;

	@ApiOperation(value = "Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.")
	@GetMapping(value="/cities/{id}")
	public ResponseEntity<Resource<CityResource>> retrieveCityById(@PathVariable Integer id) {
		
		Link selfRelLink = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(CityController.class)
				.retrieveCityById(id))
				.withSelfRel();
		CityResource city = cityService.findCityById(id);
		Resource<CityResource> resource = new Resource<>(city);
		resource.add(selfRelLink);
		return ResponseEntity.ok().body(resource);
	}
	
	@ApiOperation(value = "Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.")
	@GetMapping(value="/countries/{id}")
	public ResponseEntity<Resource<CountryResource>> retrieveCountryById(@PathVariable Integer id) {
		
		Link selfRelLink = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(CityController.class)
				.retrieveCityById(id))
				.withSelfRel();
		CountryResource country = countryService.findCountryById(id);
		Resource<CountryResource> resource = new Resource<>(country);
		resource.add(selfRelLink);
		return ResponseEntity.ok().body(resource);
	}
	
	@ApiOperation(value = "Returns either list of all countries (using /cities) or filtered countries (using /cities?country=<value>).")
	@GetMapping(value="/cities")
	public ResponseEntity<Resources<CityResource>> retrieveCitiesByCountryName(@RequestParam(value = "country", required = false) String countryName) {

		List<CityResource> cities = cityService.findCitiesByCountry(countryName);
		Resources<CityResource> resources = new Resources<>(cities);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	}
	
	@ApiOperation(value = "Returns all countries.")
	@GetMapping(value="/countries")
	public ResponseEntity<Resources<CountryResource>> retrieveAllCountries() {
		
		List<CountryResource> countries = countryService.findCountries();
		Resources<CountryResource> resources = new Resources<>(countries);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	    
	}

}
