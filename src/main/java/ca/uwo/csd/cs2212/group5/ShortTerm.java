package ca.uwo.csd.cs2212.group5;

import java.util.Calendar;
import java.util.TimeZone;

public class ShortTerm {

	private String description;
	private int temperature;
	private Calendar time;
	private String iconCode;

	private final String iconUrl = "http://openweathermap.org/img/w/";
	private final String iconUrl2 = ".png";

	public ShortTerm(String timezone) {
		time = Calendar.getInstance(TimeZone.getTimeZone(timezone));
	}

	public void setTemp(int value) {
		this.temperature = value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
	}

	public String toString(boolean unitCelsius) {
		
		StringBuilder ret = new StringBuilder();

		ret.append("<img src=\"" + iconUrl + iconCode + iconUrl2 + "\">");
		ret.append(" " + description);
		ret.append("<br/>" + "<span style=\"color:#0000cd;\"><strong>"
				+ MiscOperations.temperatureUnit(temperature, unitCelsius) + "</strong></span>");
		
		return ret.toString();
	}
}
