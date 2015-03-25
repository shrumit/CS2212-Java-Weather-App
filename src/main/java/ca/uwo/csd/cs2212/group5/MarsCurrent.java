package ca.uwo.csd.cs2212.group5;

public class MarsCurrent {

	private Parameter minTemp;
	private Parameter maxTemp;
	private Parameter windSpeed;
	private Parameter windDirection;
	private Parameter pressure;
	private Parameter humidity;
	private String description;

	private static final Parameter nullParam = new Parameter("Error", "Error",
			0);

	public MarsCurrent() {
		minTemp = maxTemp = windSpeed = windDirection = pressure = humidity = nullParam;
		description = "";
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
	 * Sets wind direction after converting it from degree to string such as "N"
	 * or "SW"
	 * 
	 * @param value
	 *            is the wind direction in degrees
	 */
	public void setWindDir(String value) {
		windDirection = new Parameter("Wind Direction", value);
	}

	/**
	 * Sets pressure
	 * 
	 * @param value
	 *            is the pressure
	 */
	public void setPressure(int value) {
		pressure = new Parameter("Air Pressure", "hPa", value);
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

	public void setCondition(String condition) {
		this.description = condition;
	}

	public String toString() {
		return ("\n" + minTemp.toString() + "\n" + maxTemp.toString()
				+ humidity.toString() + "\n" + windSpeed.toString() + "\n"
				+ windDirection.toString() + "\n" + pressure.toString() + "\n"
				+ "Conditions: \t\t" + description);
	}
}
