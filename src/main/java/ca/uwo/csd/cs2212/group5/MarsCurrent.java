package ca.uwo.csd.cs2212.group5;

/**
 * This class is to be used as a datatype object that stores various parameters
 * that describe the current weather on Mars. Getter and setter methods are
 * provided for all parameters, in addition to a constructor.
 * 
 * @author CS2212 Team 5
 *
 */
public class MarsCurrent {

	private int minTemp;
	private int maxTemp;
	private int speed;
	private String direction;
	private int pressure;
	private int humidity;
	private String description;
	private String code;

	/**
	 * Constructor
	 */
	public MarsCurrent() {
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
	 * Sets wind direction
	 * 
	 * @param value
	 *            the direction as a string
	 */
	public void setWindDir(String dir) {
		direction = dir;
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
	 *            is the minimum temperature in Celsius
	 */
	public void setMinTemp(int value) {
		minTemp = value;
	}

	/**
	 * Sets maximum temperature
	 * 
	 * @param value
	 *            is the minimum temperature in Celsius
	 */
	public void setMaxTemp(int value) {
		maxTemp = value;
	}

	/**
	 * Sets description of the weather condition.
	 * 
	 * @param description
	 *            is the description
	 */
	public void setCondition(String condition) {
		System.out.println(condition);
		if ((condition.length() == 0) || (condition == null)) {
			this.description = "Condition unavailable.";
		} else
			this.description = condition;
	}

	/**
	 * Sets iconCode using parameter.
	 * 
	 * @param code
	 *            code used by OWM to label a particular icon
	 */
	public void setIcon(String code) {
		this.code = code;
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
	public String getWindSpeed() {
		if (speed != 0)
			return Integer.toString(speed);
		else
			return "";
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

		if (cels)
			return Integer.toString(minTemp);
		else
			return Integer.toString(MiscOperations.temperatureUnit(minTemp,
					cels));

	}

	/**
	 * Returns the maximum temperature in the preferred unit.
	 * 
	 * @param cels
	 *            Celsius if true; Farhenheit if false
	 * @return maximum temperature in the preferred unit
	 */
	public String getMaxTemp(boolean cels) {
		if (cels)
			return Integer.toString(maxTemp);
		else
			return Integer.toString(MiscOperations.temperatureUnit(maxTemp,
					cels));

	}

	/**
	 * Returns a formatted string describing the weather condition.
	 * 
	 * @return String description of the weather
	 */
	public String getCondition() {
		return description;
	}

	/**
	 * Returns String representing the OWM icon code
	 * 
	 * @return icon code used by OWM
	 */
	public String getIcon() {
		return code;
	}

}
