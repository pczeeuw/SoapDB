package nl.pczeeuw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import nl.pczeeuw.domain.entities.Country;
import nl.pczeeuw.domain.repositories.CountryRepository;

@SpringBootApplication
@ComponentScan(basePackages={"nl.pczeeuw.domain.entities","nl.pczeeuw.ws"})
public class SoapdbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SoapdbApplication.class, args);
	}
	
	@Bean
	public String testDB(CountryRepository repo) {
		repo.save(new Country("Spain","Madrid",12345678));
		
		System.out.println(repo.findByName("Spain").getName());
		System.out.println(repo.findCountryByCapital("Madrid").getName());
		
		return repo.findByName("Spain").getName();
	}
}
