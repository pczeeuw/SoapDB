package nl.pczeeuw.ws.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import nl.pczeeuw.common.utils.CountryConverter;
import nl.pczeeuw.domain.repositories.CountryRepository;
import nl.pczeeuw.ws.countries.AddCountryToDBRequest;
import nl.pczeeuw.ws.countries.AllCountriesResponse;
import nl.pczeeuw.ws.countries.CountryAddedToDbResponse;
import nl.pczeeuw.ws.countries.CountryByNameRequest;
import nl.pczeeuw.ws.countries.CountryResponse;

@Endpoint
public class CountryEndpoint {

	private static final String NAMESPACE_URI = "http://pczeeuw.nl/ws/countries";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "countryByNameRequest")
	@ResponsePayload
	public CountryResponse getCountryNameByName(@RequestPayload CountryByNameRequest request) {

		CountryResponse response = new CountryResponse();
		
		nl.pczeeuw.domain.entities.Country jpaCountry = countryRepository.findByName(request.getName());

		nl.pczeeuw.ws.countries.Country wsCountry = new nl.pczeeuw.ws.countries.Country();

		response.setCountry(CountryConverter.convertJpaCountryToWsCountry(jpaCountry, wsCountry));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "allCountriesRequest")
	@ResponsePayload
	public AllCountriesResponse getAllCountries() {
		AllCountriesResponse allCountriesResponse = new AllCountriesResponse ();
		List<nl.pczeeuw.domain.entities.Country> allJPACountriesList = countryRepository.findAll();
		
		allCountriesResponse.getCountry().addAll( convertJpaToWsCountries(allJPACountriesList) );
				
		return allCountriesResponse;
	}
	
	private List<nl.pczeeuw.ws.countries.Country> convertJpaToWsCountries (List<nl.pczeeuw.domain.entities.Country> jpaCountries) {
		List<nl.pczeeuw.ws.countries.Country> wsCountries = new ArrayList<>();
		for (nl.pczeeuw.domain.entities.Country jpaCountry : jpaCountries) {
			wsCountries.add(CountryConverter.convertJpaCountryToWsCountry(jpaCountry, new nl.pczeeuw.ws.countries.Country () ));
		}
		return wsCountries;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryToDBRequest")
	@ResponsePayload
	public CountryAddedToDbResponse getCountryNameByCapital(@RequestPayload AddCountryToDBRequest request) {

		CountryAddedToDbResponse response = new CountryAddedToDbResponse();

		nl.pczeeuw.ws.countries.Country wsCountry = request.getCountry();
		
		//In case this is an update, try to retrieve the country by name from the DB first
		nl.pczeeuw.domain.entities.Country jpaCountry = countryRepository.findByName(wsCountry.getName());
		
		//If the result is null, 
		if (jpaCountry == null) {
			jpaCountry = new nl.pczeeuw.domain.entities.Country ();
		}
		
		jpaCountry = CountryConverter.convertWsCountryToJpaCountry(wsCountry,jpaCountry);
		
		try {
			countryRepository.save(jpaCountry);
			response.setSuccess(true);
		} catch (DataIntegrityViolationException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
			response.setSuccess(false);
		}

		return response;
	}

}
