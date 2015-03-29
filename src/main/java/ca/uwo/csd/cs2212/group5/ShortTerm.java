package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ShortTerm {

	private String description;
	private int temperature;
	private Calendar time;
	private String iconCode;

	/**
	 * Constructor
	 * @param timezone
	 */
	public ShortTerm(String timezone) {
		time = Calendar.getInstance(TimeZone.getTimeZone(timezone));
	}
	
	/**
	 * set temperature attribute of object as parameter
	 * @param value
	 */
	public void setTemp(int value) {
		this.temperature = value;
	}

	/**
	 * set weather condition attribute of object as parameter
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set time given parameter
	 * @param unixTime
	 */
	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	/**
	 * set icon attribute of object as parameter
	 * @param iconCode
	 */
	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
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
	 * get condition
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * get time
	 * @return
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(time.getTimeZone());
		return sdf.format(time.getTime());
	}

	/**
	 * get icon
	 */
	public String getIcon() {
		return iconCode;
	}

	/**
	 * unit change
	 * @param unitCelsius
	 * @return
	 */
	public String toString(boolean unitCelsius) {
		
		StringBuilder ret = new StringBuilder();
		ret.append(" " + description);
		ret.append("<br/>" + "<span style=\"color:#0000cd;\"><strong>"
				+ MiscOperations.temperatureUnit(temperature, unitCelsius) + "</strong></span>");
		return ret.toString();
	}
}
