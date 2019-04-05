package br.com.cinq.spring.data.sample.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cinq.spring.data.sample.application.dto.CountryDTO;
import br.com.cinq.spring.data.sample.application.services.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/rest")
@Api(value = "CountryResource")
public class CountryResource {

	@Autowired
	private CountryService service;

	@ApiOperation(value = "Returns all countries.")
	@GetMapping(value="/countries", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryDTO>> find() {

		return ResponseEntity.ok().body(service.findAll());
	}

	@ApiOperation(value = "Returns CountryDTO object by id through the /countries/{id} endpoint. Otherwise it returns a custom exception.")
	@GetMapping(value="/countries/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryDTO> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(service.findById(id));

	}
}
