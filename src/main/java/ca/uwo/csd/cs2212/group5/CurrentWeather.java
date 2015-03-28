package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONObject;

/**
 * This class constructs an object storing various weather data. The data is
 * stored in objects of the Parameter class. Time is stored in objects of the
 * Time class.
 * 
 * @author Team 5
 *
 */

public class CurrentWeather {

	private String timezone;
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
		this.timezone = timezone;
		sunrise = Calendar.getInstance(TimeZone.getTimeZone(timezone));
		sunset = Calendar.getInstance(TimeZone.getTimeZone(timezone));
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

	public void setDescription(String description) {
		this.description = description;

	}

	public void setIconCode(String code) {
		this.iconCode = code;
	}

	public void setSunset(long sunset) {
		this.sunset.setTimeInMillis(sunset * 1000);
	}

	public void setSunrise(long sunrise) {
		this.sunrise.setTimeInMillis(sunrise * 1000);
	}

	// ///////////////////////////////////////////

	public String getTemperature(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	public String getHumidity() {
		return Integer.toString(humidity);
	}

	public String getWind() {
		return Integer.toString(speed) + "km/h";
	}

	public String getPressure() {
		return Integer.toString(pressure);
	}

	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);
	}

	public String getMaxTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(maxTemp, cels);
		return Integer.toString(tempInt);
	}

	public String getWindDir() {
		return direction;
	}

	public String getSunset() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(sunset.getTimeZone());
		return sdf.format(sunset.getTime());
	}

	public String getSunrise() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(sunrise.getTimeZone());
		return sdf.format(sunrise.getTime());
	}

	public String getDescription() {
		return description;
	}

	public String getIconCode() {
		return iconCode;
	}

}
