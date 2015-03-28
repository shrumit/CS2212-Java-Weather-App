package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ShortTerm {

	private String description;
	private int temperature;
	private Calendar time;
	private String iconCode;


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
	
	public String getTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	public String getDescription() {
		return description;
	}

	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(time.getTimeZone());
		return sdf.format(time.getTime());
	}

	public String getIcon() {
		return iconCode;
	}


	public String toString(boolean unitCelsius) {
		
		StringBuilder ret = new StringBuilder();
		ret.append(" " + description);
		ret.append("<br/>" + "<span style=\"color:#0000cd;\"><strong>"
				+ MiscOperations.temperatureUnit(temperature, unitCelsius) + "</strong></span>");
		return ret.toString();
	}
}
