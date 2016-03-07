package nl.pczeeuw.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String name;
	private String capital;
	private long population;
	
	public Country () {
		
	}
	
	public Country (String name, String capital, long population) {
		this.name = name;
		this.capital = capital;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public long getPopulation() {
		return population;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

}
