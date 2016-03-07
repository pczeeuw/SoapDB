package nl.pczeeuw.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import nl.pczeeuw.domain.entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{
	
	public Country findByName (String name);

}
