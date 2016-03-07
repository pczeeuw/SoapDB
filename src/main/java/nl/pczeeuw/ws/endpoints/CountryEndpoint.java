package nl.pczeeuw.ws.endpoints;

import java.sql.SQLException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import nl.pczeeuw.common.utils.CountryConverter;
import nl.pczeeuw.domain.repositories.CountryRepository;
import nl.pczeeuw.ws.countries.AddCountryToDBRequest;
import nl.pczeeuw.ws.countries.CountryAddedToDbResponse;
import nl.pczeeuw.ws.countries.CountryByNameRequest;
import nl.pczeeuw.ws.countries.CountryNameByCapitalRequest;
import nl.pczeeuw.ws.countries.CountryNameResponse;
import nl.pczeeuw.ws.countries.CountryPopByNameRequest;
import nl.pczeeuw.ws.countries.CountryPopResponse;
import nl.pczeeuw.ws.countries.CountryRespone;

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
	public CountryRespone getCountryNameByName(@RequestPayload CountryByNameRequest request) {

		CountryRespone response = new CountryRespone();
		
		nl.pczeeuw.domain.entities.Country jpaCountry = countryRepository.findByName(request.getName());

		nl.pczeeuw.ws.countries.Country wsCountry = new nl.pczeeuw.ws.countries.Country();

		response.setCountry(CountryConverter.convertJpaCountryToWsCountry(jpaCountry, wsCountry));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "countryPopByNameRequest")
	@ResponsePayload
	public CountryPopResponse getCountryNameByName(@RequestPayload CountryPopByNameRequest request) {
		
		CountryPopResponse response = new CountryPopResponse();
		response.setPopulation(countryRepository.findByName(request.getName()).getPopulation());

		return response;
	}

	// @PayloadRoot(namespace = NAMESPACE_URI, localPart =
	// "CountryCapitalByNameRequest")
	// @ResponsePayload
	// public CountryCapitalResponse getCountryCapitalByName(@RequestPayload
	// CountryByNameRequest request) {
	// CountryCapitalResponse response = new CountryCapitalResponse();
	// response.setCapital(countryRepository.findByName(request.getName()).getCapital());
	//
	// return response;
	// }

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "countryNameByCapitalRequest")
	@ResponsePayload
	public CountryNameResponse getCountryNameByCapital(@RequestPayload CountryNameByCapitalRequest request) {
		CountryNameResponse response = new CountryNameResponse();

		response.setName(countryRepository.findCountryByCapital(request.getCapital()).getName());

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryToDBRequest")
	@ResponsePayload
	public CountryAddedToDbResponse getCountryNameByCapital(@RequestPayload AddCountryToDBRequest request) {

		CountryAddedToDbResponse response = new CountryAddedToDbResponse();

		nl.pczeeuw.ws.countries.Country wsCountry = request.getCountry();
		
		nl.pczeeuw.domain.entities.Country jpaCountry = countryRepository.findByName(wsCountry.getName());
		
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
