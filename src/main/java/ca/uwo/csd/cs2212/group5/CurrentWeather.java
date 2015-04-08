package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class is to be used as a datatype object that stores various parameters
 * that describe the current weather at a particular location. Getter and setter
 * methods are provided for all parameters, in addition to a constructor.
 * 
 * @author CS2212 Team 5
 *
 */
public class CurrentWeather {

	private TimeZone timezone;
	private int temperature;
	private int speed;
	private String direction;
	private int humidity;
	private int pressure;
	private int minTemp;
	private int maxTemp;
	private String description;
	private String iconCode;

	private Calendar sunrise;
	private Calendar sunset;

	/**
	 * Constructor that sets the timezone for the class and initializes the
	 * sunrise and sunset Calendar objects to that timezone.
	 * 
	 * @param timezone
	 *            is the timezone identifier at this location
	 */
	public CurrentWeather(String timezone) {
		this.timezone = TimeZone.getTimeZone(timezone);
		sunrise = Calendar.getInstance(this.timezone);
		sunset = Calendar.getInstance(this.timezone);
	}

	/**
	 * Sets temperature.
	 * 
	 * @param value
	 *            the temperature in Kelvin
	 */
	public void setTemperature(int value) {
		temperature = value;
	}

	/**
	 * Sets humidity.
	 * 
	 * @param value
	 *            the humidity in percent
	 */
	public void setHumidity(int value) {
		humidity = value;
	}

	/**
	 * Sets wind speed after converting from m/s to km/h
	 * 
	 * @param value
	 *            the wind speed in m/s
	 */
	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		this.speed = value;
	}

	/**
	 * Sets pressure.
	 * 
	 * @param value
	 *            the pressure in hPa
	 */
	public void setPressure(int value) {
		pressure = value;
	}

	/**
	 * Sets minimum temperature
	 * 
	 * @param value
	 *            is the minimum temperature in Kelvin
	 */
	public void setMinTemp(int value) {
		minTemp = value;
	}

	/**
	 * Sets maximum temperature
	 * 
	 * @param value
	 *            is the minimum temperature in Kelvin
	 */
	public void setMaxTemp(int value) {
		maxTemp = value;
	}

	/**
	 * Sets wind direction after converting it from degree to string such as "N"
	 * or "SW"
	 * 
	 * @param value
	 *            is the wind direction in degrees
	 */
	public void setWindDir(int value) {
		String dir = MiscOperations.convertWindDir(value);
		direction = dir;
	}

	/**
	 * Sets description of the weather condition.
	 * 
	 * @param description
	 *            is the description
	 */
	public void setDescription(String description) {
		this.description = description;

	}

	/**
	 * Sets iconCode using parameter.
	 * 
	 * @param code
	 *            code used by OWM to label a particular icon
	 */
	public void setIconCode(String code) {
		this.iconCode = code;
	}

	/**
	 * Sets sunset time
	 * 
	 * @param sunset
	 *            time in unix time
	 */
	public void setSunset(long sunset) {
		this.sunset.setTimeInMillis(sunset * 1000);
	}

	/**
	 * Sets sunrise time
	 * 
	 * @param sunrise
	 *            time in unix time
	 */
	public void setSunrise(long sunrise) {
		this.sunrise.setTimeInMillis(sunrise * 1000);
	}

	/* Getters */

	/**
	 * Returns the temperature in the preferred unit.
	 * 
	 * @param cels
	 *            Celsius if true; Farhenheit if false
	 * @return temperature in the preferred unit
	 */
	public String getTemperature(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * Returns the humidity.
	 * 
	 * @return String humidity in %
	 */
	public String getHumidity() {
		return Integer.toString(humidity);
	}

	/**
	 * Returns the wind speed.
	 * 
	 * @return String wind speed in km/h
	 */
	public String getWind() {
		return Integer.toString(speed) + "km/h";
	}

	/**
	 * Returns the pressure.
	 * 
	 * @return String pressure in hPa
	 */
	public String getPressure() {
		return Integer.toString(pressure);
	}

	/**
	 * Returns the minimum temperature in the preferred unit.
	 * 
	 * @param cels
	 *            Celsius if true; Farhenheit if false
	 * @return minimum temperature in the preferred unit
	 */
	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * Returns the maximum temperature in the preferred unit.
	 * 
	 * @param cels
	 *            Celsius if true; Farhenheit if false
	 * @return maximum temperature in the preferred unit
	 */
	public String getMaxTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(maxTemp, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * Returns a string with wind direction.
	 * 
	 * @return String direction of wind
	 */
	public String getWindDir() {
		return direction;
	}

	/**
	 * Returns a formatted string showing the sunset time.
	 * 
	 * @return String indicating sunset time formatted as "hh:mm a"
	 */
	public String getSunset() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(timezone);
		return sdf.format(sunset.getTime());
	}

	/**
	 * Returns a formatted string showing the sunrise time.
	 * 
	 * @return String indicating sunrise time formatted as "hh:mm a"
	 */
	public String getSunrise() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(timezone);
		return sdf.format(sunrise.getTime());
	}

	/**
	 * Returns a formatted string describing the weather condition.
	 * 
	 * @return String description of the weather
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns String representing the OWM icon code
	 * 
	 * @return icon code used by OWM
	 */
	public String getIconCode() {
		return iconCode;
	}

}
