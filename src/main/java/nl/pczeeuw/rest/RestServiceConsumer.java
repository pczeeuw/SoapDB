package nl.pczeeuw.rest;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestServiceConsumer {

	@Bean (name="restCountries")
	List<nl.pczeeuw.domain.entities.Country> getCountries () {
		RestTemplate template = new RestTemplate( setUpProxy() );
		nl.pczeeuw.domain.entities.Country[] countries = template.getForObject("http://restcountries.eu/rest/v1/all", nl.pczeeuw.domain.entities.Country[].class);
				
		return Arrays.asList(countries);
	}
	
	private SimpleClientHttpRequestFactory setUpProxy () {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.nl.logica.com", 80));

		clientHttpRequestFactory.setProxy(proxy);
		
		return clientHttpRequestFactory;
	}
}
