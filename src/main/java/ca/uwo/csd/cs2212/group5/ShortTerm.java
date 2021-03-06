package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Used as a datatype object that stores various parameters
 * that describe a day's weather at a particular location. Getter and setter
 * methods are provided for all parameters, in addition to a constructor.
 * 
 * @author CS2212 Team 5
 *
 */
public class ShortTerm {

	private String description;
	private TimeZone timezone;
	private int temperature;
	private Calendar time;
	private String iconCode;

	/**
	 * Constructor that sets the timezone for the class and initializes the
	 * sunrise and sunset Calendar objects to that timezone.
	 * 
	 * @param timezone
	 *            is the timezone identifier at this location
	 */
	public ShortTerm(String timezone) {
		this.timezone = TimeZone.getTimeZone(timezone);
		time = Calendar.getInstance(this.timezone);
	}

	/**
	 * Sets temperature.
	 * 
	 * @param value
	 *            the temperature in Kelvin
	 */
	public void setTemp(int value) {
		this.temperature = value;
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
	 * Sets the time for which this object stores the data
	 * 
	 * @param unixTime
	 *            the unix time in milliseconds
	 */
	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	/**
	 * Sets iconCode using parameter.
	 * 
	 * @param iconCode
	 *            code used by OWM to label a particular icon
	 */
	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
	}

	/**
	 * Returns the temperature in the preferred unit.
	 * 
	 * @param cels
	 *            Celsius if true; Farhenheit if false
	 * @return temperature in the preferred unit
	 */
	public String getTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
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
	 * Returns a formatted string showing the date for which this instance
	 * stores the weather data.
	 * 
	 * @return String indicating time formatted as "MMM dd"
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(timezone);
		return sdf.format(time.getTime());
	}

	/**
	 * Returns String representing the OWM icon code
	 * 
	 * @return icon code used by OWM
	 */
	public String getIcon() {
		return iconCode;
	}

}
