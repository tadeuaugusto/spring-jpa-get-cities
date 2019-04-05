package br.com.cinq.spring.data.sample.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cinq.spring.data.sample.application.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}