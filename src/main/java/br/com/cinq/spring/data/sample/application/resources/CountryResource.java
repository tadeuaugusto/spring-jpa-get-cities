package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.dto.CountryDTO;
import br.com.cinq.spring.data.sample.application.services.CountryService;

@RestController
@RequestMapping(value="/rest")
public class CountryResource {

	@Autowired
	private CountryService service;
	
	@RequestMapping(value="/countries", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryDTO>> find() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@RequestMapping(value="/countries/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryDTO> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(service.findById(id));

	}
}
