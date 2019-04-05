package br.com.cinq.spring.data.sample.application.dto;

import java.io.Serializable;

import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.domain.Country;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Country country;

	public CityDTO() {
		
	}
	
	public CityDTO(City city) {
		id = city.getId();
		name = city.getName();
		country = city.getCountry();
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}