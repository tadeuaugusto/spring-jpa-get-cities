package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.services.CityService;

@RestController
@RequestMapping(value="/rest")
public class CityResource {

	@Autowired
	private CityService cityService;

	@RequestMapping(value="/cities", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDTO>> find(@RequestParam(value = "country", required = false) String country) {
		
		return ResponseEntity.ok().body(cityService.findByCountry(country));
	}
	
	@RequestMapping(value="/cities/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CityDTO> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(cityService.findById(id));
	}

}
