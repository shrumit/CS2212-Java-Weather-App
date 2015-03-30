package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class constructs an object storing various weather data. The data is
 * stored in objects of the Parameter class. Time is stored in objects of the
 * Time class.
 * 
 * @author Team 5
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
	 * Initializes everything to null.
	 */
	public CurrentWeather(String timezone) {
		this.timezone = TimeZone.getTimeZone(timezone);
		sunrise = Calendar.getInstance(this.timezone);
		sunset = Calendar.getInstance(this.timezone);
	}

	/**
	 * Sets temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setTemperature(int value) {
		temperature = value;
	}

	/**
	 * Sets humidity
	 * 
	 * @param value
	 *            is the humidity
	 */
	public void setHumidity(int value) {
		humidity = value;
	}

	/**
	 * Sets wind speed after converting from m/s to km/h
	 * 
	 * @param value
	 *            is the wind speed in m/s
	 */
	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		this.speed = value;
	}

	/**
	 * Sets pressure
	 * 
	 * @param value
	 *            is the pressure
	 */
	public void setPressure(int value) {
		pressure = value;
	}

	/**
	 * Sets minimum temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setMinTemp(int value) {
		minTemp = value;
	}

	/**
	 * Sets maximum temperature
	 * 
	 * @param value
	 *            is the temperature
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
	 * Sets Condition using parameter
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;

	}

	/**
	 * Sets iconCode using parameter.
	 * 
	 * @param code
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

	// ///////////////////////////////////////////

	/**
	 * Returns the temperature in the preferred unit.
	 * 
	 * @return String temperature
	 */
	public String getTemperature(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * Returns the humidity.
	 * 
	 * @return String humidity
	 */
	public String getHumidity() {
		return Integer.toString(humidity);
	}

	/**
	 * Returns the wind speed.
	 * 
	 * @return String wind speed
	 */
	public String getWind() {
		return Integer.toString(speed) + "km/h";
	}

	/**
	 * Returns the pressure.
	 * 
	 * @return String pressure
	 */
	public String getPressure() {
		return Integer.toString(pressure);
	}

	/**
	 * Returns a minimum temperature in the preferred unit.
	 * 
	 * @return String minimum temperature
	 */
	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * Returns a maximum temperature in the preferred unit.
	 * 
	 * @return String maximum temperature
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
	 * @return String indicating sunset time.
	 */
	public String getSunset() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(timezone);
		return sdf.format(sunset.getTime());
	}

	/**
	 * Returns a formatted string showing the sunrise time.
	 * 
	 * @return String indicating sunrise time.
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
	 * get iconCode
	 * 
	 * @return
	 */
	public String getIconCode() {
		return iconCode;
	}

}
