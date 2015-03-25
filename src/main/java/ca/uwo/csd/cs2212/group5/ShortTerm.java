package ca.uwo.csd.cs2212.group5;

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
		time.setTimeInMillis(unixTime*1000);
	}

	public void setIcon(String iconCode){
		this.iconCode = iconCode;
	}

	public String toString() {
		return description;
	}

}
