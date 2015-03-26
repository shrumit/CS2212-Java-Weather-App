package ca.uwo.csd.cs2212.group5;

public class City {
	private String city;
	private String country;
	private int id;
	
	public City(String city, String country, int id){
		this.city = city;
		this.country = country;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		return (city + ", " + country);
	}
}
