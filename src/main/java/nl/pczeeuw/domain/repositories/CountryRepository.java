package nl.pczeeuw.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nl.pczeeuw.domain.entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{
	
	public Country findByName (String name);
	
	public Country findCountryByCapital (String capital);
	
	public List<Country> findAll ();

}
