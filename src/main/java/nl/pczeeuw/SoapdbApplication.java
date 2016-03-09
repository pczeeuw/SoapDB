package nl.pczeeuw;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages={"nl.pczeeuw.domain.entities","nl.pczeeuw.ws","nl.pczeeuw.rest"})
public class SoapdbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SoapdbApplication.class, args);
	}
	
//	@Bean
//	public String testDB(CountryRepository repo) {
//		repo.save(new Country("Spain","Madrid",12345678));
//		
//		System.out.println(repo.findByName("Spain").getName());
//		System.out.println(repo.findCountryByCapital("Madrid").getName());
//		
//		return repo.findByName("Spain").getName();
//	}

	@Bean
	public String testCountryRestList (ApplicationContext appContext) {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.nl.logica.com", 80));
		
		clientHttpRequestFactory.setProxy(proxy);
		
		
		RestTemplate template = new RestTemplate(clientHttpRequestFactory);
		
		
		
		nl.pczeeuw.rest.objects.Country[] countries = template.getForObject("http://restcountries.eu/rest/v1/all", nl.pczeeuw.rest.objects.Country[].class);
		
		
		
		for (nl.pczeeuw.rest.objects.Country c : countries) {
			System.out.println("Name of country = " + c.getName());
			System.out.println("Capital = " + c.getCapital());
			System.out.println("Population = " + c.getPopulation());
			System.out.println("-----------------------------------");
		}
		
		return null;
	}
	
}
