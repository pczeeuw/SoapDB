package nl.pczeeuw.rest.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Country {
	
	private String name;
	
	private String capital;
	
	private long population;

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public long getPopulation() {
		return population;
	}
	
	

}
