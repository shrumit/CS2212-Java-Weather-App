package ca.uwo.csd.cs2212.group5;

/**
 * This class is to be used as a datatype object that stores various parameters
 * related to a particular location viz. the city name, country code and OWM
 * city id. In addition to a constructor, only methods pertinent to this
 * object's use in the weather project are provided (getId() and toString())
 * 
 * @author CS2212 Team 5
 *
 */
public class City {
	private String city;
	private String country;
	private int id;

	/**
	 * Class constructor
	 * 
	 * @param city
	 *            name of the city
	 * @param country
	 *            two letter country code
	 * @param id
	 *            integer storing unique id code
	 */
	public City(String city, String country, int id) {
		this.city = city;
		this.country = country;
		this.id = id;
	}

	/**
	 * Returns the city id that can then be used to fetch data from the OWM api
	 * for a particular city
	 * 
	 * @return the city id as an int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the city name and country code in a single, formatted string.
	 * 
	 * @return the string containing name and country code
	 */
	public String toString() {
		return (city + ", " + country);
	}
}
