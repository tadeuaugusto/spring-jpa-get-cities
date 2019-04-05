package br.com.cinq.sample.springjpajersey;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.application.dto.CountryDTO;
import br.com.cinq.spring.data.sample.application.repositories.CountryRepository;
import br.com.cinq.spring.data.sample.application.services.CountryService;

public class CountryServiceTest extends SpringjpaApplicationTests {

	@Autowired
    private CountryService service;

	@Autowired
    private CountryRepository repo;

	@Before
	public void setup() {
		Assert.assertNotNull(service);
    	Assert.assertNotNull(repo);
	}
    
    @Test
    public void testGelAllCountries() {

        long count = repo.count();
        Assert.assertTrue(count > 0);

        List<CountryDTO> countries = service.findAll();

        Assert.assertEquals((int) count, countries.size());
    }

    @Test
    public void testGetOneCountry() {
    	
    	CountryDTO oneCountry = service.findById(1);
    	Assert.assertNotNull(oneCountry);
    }
}
