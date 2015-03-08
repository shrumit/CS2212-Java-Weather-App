/**
 * CS2212B - Abstract for the LocalWeather class
 * @author jxin6
 */
public abstract class AbstractLocalWeather{

  private double time, hours, mins, temp, minTemp, maxTemp, sunriseTime, sunsetTime, windSpeed, windDirection, airPressure, humidity;
  	private int year, month, date;
  	
  	public LocalWeather (int year, int month, int date, double time, double hours, double mins, double minTemp, double maxTemp, double sunriseTime, double sunsetTime, double windSpeed, double windDirection, double airPressure, double humidity)
  	{  		
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
  	
  	/** gets the current time **/
  	abstract private double getTime();
  	
  	/** gets the current temperature **/
  	abstract private double getTemp();
  	
  	/** gets the local minimum temperature **/
  	abstract private double getminTemp();
  	
  	/** gets the local maximum temperature **/
  	abstract private double getmaxTemp();
  	
  	/** gets the local sunrise time **/
  	abstract private double getsunriseTime();
  	
  	/** gets the local sunset time **/
  	abstract private double getsunsetTime();
  	
  	/** gets the local wind speed **/
  	abstract private double windSpeed();
  	
  	/** gets the local wind direction **/
  	abstract private double windDirection();
  	
  	/** gets the local air pressure **/
  	abstract private double airPressure();
  	
  	/** gets the local humidity **/
  	abstract private double humidity();
  	
  }

}
