/**
 * 
 */

package ca.uwo.csd.cs2212.group5;

public class Time {
	
	private int hour;
	private int minute;
	private Boolean isPM;

	
	public Time (int hour, int minute){
		this.hour = hour;
		this.minute = minute;
		
		if ((hour<12) && (hour > 0))
			isPM = false;
		else isPM = true;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public boolean isDay(){
		if ((hour >=6) && (hour <= 18))
			return true;
		
		else return false;
	}
	
	public String toString()
	{		
		if (isPM)
			return ((hour-12) + ":" + minute + " " + "PM");
		else 
			return ((hour) + ":" + minute + " " + "AM");
		
	}

}
