package br.com.cinq.sample.springjpajersey.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.cinq.spring.data.sample.application.SpringjpaApplication;
import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.domain.Country;
import br.com.cinq.spring.data.sample.application.resources.CityResource;
import br.com.cinq.spring.data.sample.application.resources.CountryResource;
import br.com.cinq.spring.data.sample.application.services.CityService;
import br.com.cinq.spring.data.sample.application.services.CountryService;

/**
 * SpringjpaApplicationTestsUT covers the Unit Tests for the application.
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@WebMvcTest
public class SpringjpaApplicationTestsUT extends SpringjpaApplication {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CityService cityService;

	@MockBean
	CountryService countryService;

	Country country = null;
	City city = null;
	CityResource mockCity = null;
	CountryResource mockCountry = null;
	
	@Before
	public void setup() {
		this.country = new Country(6, "Czech Republic");
		this.city = new City(14, "Prague", country);
		this.mockCity = new CityResource(city);
		this.mockCountry = new CountryResource(country);
	}
	
	@Test
	public void testRetrieveCityById() throws Exception {
		Mockito.when(
				cityService.findCityById(Mockito.anyInt())).thenReturn(mockCity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/rest/cities/14").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\": 14, \"name\": \"Prague\", \"country\": {\"id\": 6, \"name\": \"Czech Republic\"}}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void testRetrieveCountryById() throws Exception {

		Mockito.when(
				countryService.findCountryById(Mockito.anyInt())).thenReturn(mockCountry);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/rest/countries/6").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\": 6, \"name\": \"Czech Republic\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void testRetrieveCitiesByCountry() throws Exception {

		List<CityResource> mockCities = new ArrayList<CityResource>();
		mockCities.add(mockCity);
		
		Mockito.when(
				cityService.findCitiesByCountry(Mockito.anyString())).thenReturn(mockCities);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/rest/cities?country=Czech").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\": 14, \"name\": \"Prague\", \"country\": {\"id\": 6, \"name\": \"Czech Republic\"}}]";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
