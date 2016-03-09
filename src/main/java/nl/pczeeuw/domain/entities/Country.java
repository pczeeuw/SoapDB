package nl.pczeeuw.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Country {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String name;
	private String capital;
	private String region;
	private String subregion;
	
	private long population;
	
	public Country () {
		
	}
	
	public Country (String name, String capital, String region, String subregion, long population) {
		this.name = name;
		this.capital = capital;
		this.population = population;
	}
	
	public Country (String name, String capital, long population) {
		this(name, capital, "", "", population);		
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

}
