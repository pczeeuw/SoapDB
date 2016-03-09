package nl.pczeeuw.common.utils;

/**
 * Helper class for converting JPA Country object into WS Country object and
 * vice versa.
 * 
 * @author zeeuwp
 *
 */
public class CountryConverter {

	/**
	 * Convert a JPA Country entity into a WS (Jaxb generated) Country entity
	 * 
	 * @param jpaCountryIn
	 * @param wsCountryOut
	 * @return
	 */
	public static nl.pczeeuw.ws.countries.Country convertJpaCountryToWsCountry(
			nl.pczeeuw.domain.entities.Country jpaCountryIn, nl.pczeeuw.ws.countries.Country wsCountryOut) {
		wsCountryOut.setName(jpaCountryIn.getName());
		wsCountryOut.setCapital(jpaCountryIn.getCapital());
		wsCountryOut.setPopulation(jpaCountryIn.getPopulation());

		return wsCountryOut;
	}

	/**
	 * Convert a Ws (JaxB generated) Country entity into a JPA Country entity
	 * @param wsCountryIn
	 * @param jpaCountryOut
	 * @return
	 */
	public static nl.pczeeuw.domain.entities.Country convertWsCountryToJpaCountry(
			nl.pczeeuw.ws.countries.Country wsCountryIn, nl.pczeeuw.domain.entities.Country jpaCountryOut) {

		jpaCountryOut.setName(wsCountryIn.getName());
		jpaCountryOut.setCapital(wsCountryIn.getCapital());
		jpaCountryOut.setPopulation(wsCountryIn.getPopulation());

		return jpaCountryOut;

	}
}
