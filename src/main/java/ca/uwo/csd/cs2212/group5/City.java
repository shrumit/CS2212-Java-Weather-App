package ca.uwo.csd.cs2212.group5;

public class City {
	private String city;
	private String country;
	private int id;
	
	/**
	 * constructor
	 * @param city
	 * @param country
	 * @param id
	 */
	public City(String city, String country, int id){
		this.city = city;
		this.country = country;
		this.id = id;
	}
	
	/**
	 * get city ID
	 * @return
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * convert to string format
	 */
	public String toString(){
		return (city + ", " + country);
	}
}
