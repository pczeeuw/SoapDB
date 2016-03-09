package nl.pczeeuw.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class RestServiceConsumer {

	//@Bean (name="restCountries")
	List<nl.pczeeuw.rest.objects.Country> getCountries () {
		RestTemplate template = new RestTemplate();
		nl.pczeeuw.rest.objects.Country[] countries = template.getForObject("https://restcountries.eu/rest/v1/all", nl.pczeeuw.rest.objects.Country[].class);
				
		return Arrays.asList(countries);
	}
}
