/**
 * 
 */

package ca.uwo.csd.cs2212.group5;

public class Parameter {
	
	private String type;
	private String unit;
	private double value;
	
	public Parameter(String type, String unit, double value)
	{
		this.type = type;
		this.unit = unit;
		this.value = value;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String toString()
	{
		return (value + " " + unit);
	}

}
