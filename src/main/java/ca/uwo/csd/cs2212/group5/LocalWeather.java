/**
 * CS2212B - LocalWeather class
 * @author dtsui8, jxin6
 */

import org.json.JSONObject;

public class LocalWeather extends AbstractLocalWeather{

	private double time, hours, mins, temp, minTemp, maxTemp, sunriseTime, sunsetTime, windSpeed, windDirection, airPressure, humidity;
	private int year, month, date;
	
	public LocalWeather (JSONObject json){
		this.year = year;
		this.month = month;
		this.date = date;
		this.time = time;
		this.hours = hours;
		this.mins = mins;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.sunriseTime = sunriseTime;
		this.sunsetTime = sunsetTime;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.airPressure = airPressure;
		this.humidity = humidity;
	}
	
	private double getTime( ){
		return time;
	}
	
	private double getTemp(){
		return temp;
	}
	
	private double getminTemp( ){
		return minTemp;
	}
	
	private double getmaxTemp( ){
		return minTemp;
	}
	
	private double getsunriseTime( ){
		return minTemp;
	}
	
	private double getsunsetTime( ){
		return sunsetTime;
	}
	
	private double windSpeed ( ){
		return windSpeed;
	}
	
	private double windDirection ( ){
		return windDirection;
	}
	
	private double airPressure ( ){
		return airPressure;
	}
	
	private double humidity ( ){
		return humidity;
	}
	
}