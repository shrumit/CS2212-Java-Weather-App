/**
 * 
 */

package ca.uwo.csd.cs2212.group5;

public class Parameter {

	private String type;
	private String unit;
	private double value;

	public Parameter(String type, String unit, double value) {
		this.type = type;
		this.unit = unit;
		this.value = value;
	}

	public Parameter(String type, String unit) {
		this.type = type;
		this.unit = unit;
		this.value = (double) -999;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		if (value == -999.00)
			return (type + ": \t" + unit);

		else
			return (type + ": \t\t" + value + " " + unit);
	}

}
