package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class LongTerm {

	private int temperature;
	private int minTemp;
	private int maxTemp;
	private Calendar time;
	private String description;
	private String iconCode;
	private TimeZone timezone;

	/**
	 * constructor
	 * 
	 * @param timezone
	 */
	public LongTerm(String timezone) {
		this.timezone = TimeZone.getTimeZone(timezone);
		time = Calendar.getInstance(this.timezone);
	}

	/**
	 * Set temperature as value of parameter
	 * 
	 * @param temperature
	 */
	public void setTemp(int temperature) {
		this.temperature = temperature;
	}

	/**
	 * Set min temp as value of parameter
	 * 
	 * @param value
	 */
	public void setMinTemp(int value) {
		this.minTemp = value;
	}

	/**
	 * Set max temp as value of parameter
	 * 
	 * @param value
	 */
	public void setMaxTemp(int value) {
		this.maxTemp = value;
	}

	/**
	 * Set time as value of parameter
	 * 
	 * @param unixTime
	 */
	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	/**
	 * Set condition as value of parameter
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set iconCode as value of parameter
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
	 * Returns a formatted string showing the time.
	 * 
	 * @return String indicating time.
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
		sdf.setTimeZone(timezone);
		return sdf.format(time.getTime());
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
	public String getIcon() {
		return iconCode;
	}

}
