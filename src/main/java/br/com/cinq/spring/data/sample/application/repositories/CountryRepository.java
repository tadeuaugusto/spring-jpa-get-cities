package br.com.cinq.spring.data.sample.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.application.domain.Country;

/**
 * CountryRepository
 * 
 * @author Tadeu Augusto Dutra Pinto
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

	@Query("select country from Country country WHERE country.name like %?1%")
	List<Country> findLikeName(String param);
}