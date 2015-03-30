package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ShortTerm {

	private String description;
	private TimeZone timezone;
	private int temperature;
	private Calendar time;
	private String iconCode;

	/**
	 * Constructor
	 * 
	 * @param timezone
	 */
	public ShortTerm(String timezone) {
		this.timezone = TimeZone.getTimeZone(timezone);
		time = Calendar.getInstance(this.timezone);
	}

	/**
	 * Set temperature attribute of object as parameter
	 * 
	 * @param value
	 */
	public void setTemp(int value) {
		this.temperature = value;
	}

	/**
	 * Set weather condition attribute of object as parameter
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set time given parameter
	 * 
	 * @param unixTime
	 */
	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	/**
	 * Set icon attribute of object as parameter
	 * 
	 * @param iconCode
	 */
	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
	}

	/**
	 * Returns the temperature in the preferred unit.
	 * 
	 * @return String temperature
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
	 * Returns a formatted string showing the time.
	 * 
	 * @return String indicating time.
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(timezone);
		return sdf.format(time.getTime());
	}

	/**
	 * Returns the icon code
	 * 
	 * @return String icon code
	 */
	public String getIcon() {
		return iconCode;
	}

}
