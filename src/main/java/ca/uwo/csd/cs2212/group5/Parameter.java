package ca.uwo.csd.cs2212.group5;

/**
 * This class is an object that stores weather data as three fields: the type of
 * data, the unit of the data and the data value itself. *
 * 
 * @author Team 5
 *
 */
public class Parameter {

	private String type;
	private String unit;
	private int value;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            is type of data
	 * @param unit
	 *            is the unit of the data
	 * @param value
	 *            is the data value
	 */
	public Parameter(String type, String unit, int value) {
		this.type = type;
		this.unit = unit;
		this.value = value;
	}

//	/**
//	 * This overload contructor is for wind direction, which does not have a
//	 * unit. Because wind direction data is itself a String, it is stored
//	 * instead of a data type and the value field is given -999 in order to
//	 * identify it as being null.
//	 * 
//	 * @param type
//	 *            is the type of data
//	 * @param unit
//	 *            is the unit of the data
//	 */
//	public Parameter(String type, String unit) {
//		this.type = type;
//		this.unit = unit;
//		this.value = -999;
//	}

	/**
	 * Returns the type of parameter
	 * 
	 * @return the type of parameter
	 */
	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	/**
	 * Returns a formatted toString containing all three data fields
	 */
	public String toString() {
		if (type.equalsIgnoreCase("Wind Direction"))
			return ("<b>" + type + "</b>" + ": \t" + unit);

		else if (type.equalsIgnoreCase("Temperature"))
			return ("<b>" + type + "</b>" + ": \t" + value + " " + unit);

		else
			return ("<b>" + type + "</b>" + ": \t\t" + value + " " + unit);
	}

}
