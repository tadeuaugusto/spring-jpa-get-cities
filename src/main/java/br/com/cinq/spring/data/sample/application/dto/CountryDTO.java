package br.com.cinq.spring.data.sample.application.dto;

import java.io.Serializable;

import br.com.cinq.spring.data.sample.application.domain.Country;

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public CountryDTO() {
		
	}
	
	public CountryDTO(Country country) {
		id = country.getId();
		name = country.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
