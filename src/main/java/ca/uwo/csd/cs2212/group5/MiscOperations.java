/**
 * 
 */

package ca.uwo.csd.cs2212.group5;

public class MiscOperations {

	// converts windspeed from m/s to km/h
	public static double windConvert(double mps) {
		return mps * 3.6;
	}

	// converts from kelvin to celsius
	public static int tempToCelsius(int kelvin) {
		return (int) (kelvin - 273.15);
	}

}
