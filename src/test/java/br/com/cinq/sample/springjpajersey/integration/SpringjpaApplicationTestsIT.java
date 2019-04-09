package br.com.cinq.sample.springjpajersey.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.cinq.sample.springjpajersey.SpringjpaApplicationTests;
import br.com.cinq.spring.data.sample.application.SpringjpaApplication;
import br.com.cinq.spring.data.sample.application.domain.City;

/**
 * SpringjpaApplicationTestsIT covers the Integration Tests for the application.
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@SpringBootTest(
		webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes=SpringjpaApplication.class)
public class SpringjpaApplicationTestsIT extends SpringjpaApplicationTests {

	@LocalServerPort
	protected int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveDetailsForCity() throws Exception {

		String EXPECTED_RESULT_CITIES = "{\"id\": 14, \"name\": \"Prague\", \"country\": {\"name\": \"Czech Republic\"}, \"_links\": {\"self\": {\"href\": \"http://localhost:"+port+"/rest/cities/14\"}}}";
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/cities/14"), 
				HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(EXPECTED_RESULT_CITIES, response.getBody(), false);
	}
	
	@Test
	public void testRetrieveDetailsForCountry() throws Exception {

		String EXPECTED_RESULT_COUNTRIES = "{\"id\": 6, \"name\": \"Czech Republic\", \"_links\": {\"self\": {\"href\": \"http://localhost:"+port+"/rest/cities/6\"}}}";
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/countries/6"), 
				HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(EXPECTED_RESULT_COUNTRIES, response.getBody(), false);
	}
	
	@Test
	public void testRetrieveDetailsForCitiesByCountry() throws Exception {
		
		String EXPECTED_RESULT_CITIES_BY_COUNTRY = "{\"_embedded\": {\"cityResources\": [{\"id\": 14, \"name\": \"Prague\", \"country\": {\"name\": \"Czech Republic\"}}]}, \"_links\": {\"self\": {\"href\": \"http://localhost:"+port+"/rest/cities?country=Czech\"}}}";
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/cities?country=Czech"), 
				HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(EXPECTED_RESULT_CITIES_BY_COUNTRY, response.getBody(), false);
	}
	
	@Test
    public void testRestTemplateWhenSendGetForEntityThenStatus200() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<City> response = testRestTemplate.getForEntity(createURLWithPort("/rest/cities/1"), City.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
	
	@Test
    public void testRestTemplateWhenSendGetForEntityThenStatus500() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<City> response = testRestTemplate.getForEntity(createURLWithPort("/rest/cities/99"), City.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }

	private String createURLWithPort(String uri) {
		return "http://localhost:"+port+uri;
	}

}
