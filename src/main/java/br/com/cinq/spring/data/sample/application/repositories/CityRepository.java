package br.com.cinq.spring.data.sample.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.application.domain.City;

/**
 * CountryRepository
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

	@Query("select city from City city JOIN city.country country WHERE city.country.id = country.id AND country.name like %?1%")
	List<City> findByCountry(String country);
}