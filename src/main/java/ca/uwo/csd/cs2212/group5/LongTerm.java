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

	public LongTerm(String timezone) {
		time = Calendar.getInstance(TimeZone.getTimeZone(timezone));
	}

	public void setTemp(int value) {
		this.temperature = value;
	}

	public void setMinTemp(int value) {
		this.minTemp = value;
	}

	public void setMaxTemp(int value) {
		this.maxTemp = value;
	}

	public void setTime(long unixTime) {
		time.setTimeInMillis(unixTime * 1000);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIcon(String iconCode) {
		this.iconCode = iconCode;
	}

	public String toString(boolean isCelsius) {
		return description;
	}

	public String getTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(temperature, cels);
		return Integer.toString(tempInt);
	}

	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);
	}

	public String getMaxTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(maxTemp, cels);
		return Integer.toString(tempInt);
	}

	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
		sdf.setTimeZone(time.getTimeZone());
		return sdf.format(time.getTime());
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return iconCode;
	}

}
