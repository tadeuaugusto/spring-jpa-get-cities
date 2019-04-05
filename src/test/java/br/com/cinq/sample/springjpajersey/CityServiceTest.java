package br.com.cinq.sample.springjpajersey;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.application.domain.City;
import br.com.cinq.spring.data.sample.application.dto.CityDTO;
import br.com.cinq.spring.data.sample.application.repositories.CityRepository;
import br.com.cinq.spring.data.sample.application.services.CityService;

public class CityServiceTest extends SpringjpaApplicationTests {

	@Autowired
    private CityService service;

	@Autowired
    private CityRepository repo;

	@Before
	public void setup() {
		Assert.assertNotNull(service);
    	Assert.assertNotNull(repo);
	}
	
	@Test
    public void testCitiesByCountry() {
    	
    	long count = repo.count();
    	Assert.assertTrue(count > 0);

    	City city = new City();
    	city.setName("Brazil");

    	List<City> list = repo.findAll();

    	Assert.assertEquals((int) count, list.size());
    }
    
    @Test
    public void testGetCityById() {

    	City city = new City();
    	city.setName("Brazil");

    	List<CityDTO> list = service.findByCountry(city.getName());

    	Assert.assertEquals(3, list.size());
    }
}
