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

	/**
	 * constructor
	 * @param timezone
	 */
	public LongTerm(String timezone) {
		time = Calendar.getInstance(TimeZone.getTimeZone(timezone));
	}

	/**
	 * set temperature as value of parameter
	 * @param value
	 */
	public void setTemp(int value) {
		this.temperature = value;
	}

	/**
	 * set min temp as value of parameter
	 * @param value
	 */
	public void setMinTemp(int value) {
		this.minTemp = value;
	}

	/**
	 * set max temp as value of parameter
	 * @param value
	 */
	public void setMaxTemp(int value) {
		this.maxTemp = value;
	}

	/**
	 * set time as value of parameter
	 * @param unixTime
	 */
	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	/**
	 * set condition as value of parameter
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set iconCode as value of parameter
	 * @param iconCode
	 */
	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
	}

	/**
	 * return description as a string
	 * @param isCelsius
	 * @return
	 */
	public String toString(boolean isCelsius) {
		return description;
	}

	/**
	 * get temperature
	 * @param cels
	 * @return
	 */
	public String getTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * get min temp
	 * @param cels
	 * @return
	 */
	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * get max temp
	 * @param cels
	 * @return
	 */
	public String getMaxTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(maxTemp, cels);
		return Integer.toString(tempInt);
	}

	/**
	 * get time
	 * @return
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
		sdf.setTimeZone(time.getTimeZone());
		return sdf.format(time.getTime());
	}

	/**
	 * get condition
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/** 
	 * get iconCode
	 * @return
	 */
	public String getIcon() {
		return iconCode;
	}

}
