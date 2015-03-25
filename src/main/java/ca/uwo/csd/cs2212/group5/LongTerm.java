package ca.uwo.csd.cs2212.group5;

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
		time.setTimeInMillis(unixTime*1000);
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setIcon(String iconCode){
		this.iconCode = iconCode;
	}

	public String toString() {
		return description;
	}

}
