package ca.uwo.csd.cs2212.group5;

import java.awt.Image;

/**
 * This class constructs an object storing various weather data. The data is
 * stored in objects of the Parameter class. Time is stored in objects of the
 * Time class.
 * 
 * @author Team 5
 *
 */

public class CurrentWeather {

	private Parameter temperature;
	private Parameter humidity;
	private Parameter windSpeed;
	private Parameter windDirection;
	private Parameter airPressure;
	private Parameter minTemp;
	private Parameter maxTemp;
	private Time riseTime;
	private Time setTime;
	private Time time;
	private Image condition;
	private String description;

	public CurrentWeather() {

	}

	/**
	 * Sets temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setTemperature(int value) {
		value = MiscOperations.tempToCelsius(value);
		temperature = new Parameter("Temperature", "Celsius", value);
	}

	/**
	 * Sets humidity
	 * 
	 * @param value
	 *            is the humidity
	 */
	public void setHumidity(int value) {
		humidity = new Parameter("Humidity", "%", value);
	}

	/**
	 * Sets wind speed after converting from m/s to km/h
	 * 
	 * @param value
	 *            is the wind speed in m/s
	 */
	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		windSpeed = new Parameter("Wind Speed", "km/h", value);
	}

	/**
	 * Sets pressure
	 * 
	 * @param value
	 *            is the pressure
	 */
	public void setPressure(int value) {
		airPressure = new Parameter("Air Pressure", "hPa", value);
	}

	/**
	 * Sets minimum temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setMinTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		minTemp = new Parameter("Min Temp", "Celsius", value);
	}

	/**
	 * Sets maximum temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setMaxTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		maxTemp = new Parameter("Max Temp", "Celsius", value);
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
		windDirection = new Parameter("Wind Direction", dir);
	}

	/**
	 * Outputs data from all the variables as a single string.
	 */
	public String toString() {
		return (temperature.toString() + "\n" + humidity.toString() + "\n"
				+ windSpeed.toString() + "\n" + windDirection.toString() + "\n"
				+ airPressure.toString() + "\n" + minTemp.toString() + "\n" + maxTemp
					.toString());

	}
}
