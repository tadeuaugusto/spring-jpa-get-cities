package br.com.cinq.sample.springjpajersey.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
public class SpringjpaApplicationTestsIT extends SpringjpaApplication {

	protected static final String localhost = "http://localhost:";

	@LocalServerPort
	protected int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveDetailsForCity() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/cities/14"), 
				HttpMethod.GET, entity, String.class);

		String expected = "{\"id\": 14, \"name\": \"Prague\", \"country\": {\"id\": 6, \"name\": \"Czech Republic\"}}";

		// JSONAssert.assertEquals(expected, response.getBody(), false);
		assertThat(expected, equalTo(response.getBody()));
	}
	
	@Test
	public void testRetrieveDetailsForCountry() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/countries/6"), 
				HttpMethod.GET, entity, String.class);

		String expected = "{\"id\": 6, \"name\": \"Czech Republic\"}";

		// JSONAssert.assertEquals(expected, response.getBody(), false);
		assertThat(expected, equalTo(response.getBody()));
	}
	
	@Test
	public void testRetrieveDetailsForCitiesByCountry() throws Exception {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/rest/cities?country=Czech"), 
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"id\": 14, \"name\": \"Prague\", \"country\": {\"id\": 6, \"name\": \"Czech Republic\"}}]";
		
		// JSONAssert.assertEquals(expected, response.getBody(), false);
		assertThat(expected, equalTo(response.getBody()));
	}
	
	@Test
    public void testRestTemplateWhenSendGetForEntityThenStatusOk() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<City> response = testRestTemplate.getForEntity(localhost + this.port + "/rest/cities/1", City.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

	private String createURLWithPort(String uri) {
		return "http://localhost:"+port+uri;
	}

}
