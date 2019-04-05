package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/rest")
@Api(value = "CityResource")
public class CityResource {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Returns either list of all countries or filtered countries using /cities?country=<value>.")
	@GetMapping(value="/cities",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDTO>> find(@RequestParam(value = "country", required = false) String country) {
		
		return ResponseEntity.ok().body(cityService.findByCountry(country));
	}
	
	@ApiOperation(value = "Returns CityDTO object by id through the /cities/{id} endpoint. Otherwise found it returns a custom exception.")
	@GetMapping(value="/cities/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CityDTO> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(cityService.findById(id));
	}

}
