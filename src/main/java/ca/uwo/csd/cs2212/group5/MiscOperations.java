package ca.uwo.csd.cs2212.group5;

import java.util.Calendar;

/**
 * This static class provides various conversion operations for the data
 * retrieved from the API.
 * 
 * @author Team 5
 *
 */
public class MiscOperations {

	// for the conversion of wind directions
	private static double N = 0;
	private static double NNE = 22.5;
	private static double NE = 45;
	private static double ENE = 67.5;
	private static double E = 90;
	private static double ESE = 112.5;
	private static double SE = 135;
	private static double SSE = 157.5;
	private static double S = 180;
	private static double SSW = 202.5;
	private static double SW = 225;
	private static double WSW = 247.5;
	private static double W = 270;
	private static double WNW = 292.5;
	private static double NW = 315;
	private static double NNW = 338;

	/**
	 * Converts wind speed from m/s to km/h
	 * 
	 * @param mps
	 *            is wind speed in m/s
	 * @return wind speed in km/h
	 */
	public static int windConvert(int mps) {
		return (int) (mps * 3.6);
	}

	/**
	 * Converts temperature from Kelvin to Celsius
	 * 
	 * @param kelvin
	 *            is temperature in Kelvin
	 * @return the temperature in Celsius
	 */
	public static int tempToCelsius(int kelvin) {
		return (int) (kelvin - 273.15);
	}
	

	/**
	 * Takes Calendar object and returns a String of format HH : MM
	 * 
	 * @param time is Calendar object representing time
	 * @return String representation of time
	 */
	public static String displayTime(Calendar time) {
		String hour = String.format("%02d", time.get(Calendar.HOUR));
		String minute = String.format("%02d", time.get(Calendar.MINUTE));
		
		return (hour + " : " + minute);
	}


	/**
	 * 
	 * @param dir
	 *            is wind direction in degrees
	 * @return String storing letter representing wind direction
	 */
	public static String convertWindDir(double dir) {
		// N = 0/360 +- 11.25
		if (dir >= (NNW + 11.25) && dir <= 360 || dir >= 0
				&& dir <= (NNE - 11.25)) {
			return "N";
		}

		// NNE = 22.5 +- 11.25
		else if (dir > (NNE - 11.25) && dir <= (NNE + 11.25)) {
			return "NNE";
		}

		// NE = 45 +- 11.25
		else if (dir > (NE - 11.25) && dir <= (NE + 11.25)) {
			return "NE";
		}

		// ENE = 67.5 +- 11.25
		else if (dir > (ENE - 11.25) && dir <= (ESE + 11.25)) {
			return "ENE";
		}

		// E = 90 +- 11.25
		else if (dir > (E - 11.25) && dir <= (E + 11.25)) {
			return "E";
		}

		// ESE = 112.5 +- 11.25
		else if (dir > (ESE - 11.25) && dir <= (ESE + 11.25)) {
			return "ESE";
		}

		// SE = 135 +- 11.25
		else if (dir > (SE - 11.25) && dir <= (SE + 11.25)) {
			return "SE";
		}

		// SSE = 157.5 +- 11.25
		else if (dir > (SSE - 11.25) && dir <= (SSE + 11.25)) {
			return "SSE";
		}

		// S = 180 +- 11.25
		else if (dir > (S - 11.25) && dir <= (S + 11.25)) {
			return "S";
		}

		// SSW = 202.5 +- 11.25
		else if (dir > (SSW - 11.25) && dir <= (SSW + 11.25)) {
			return "SSW";
		}

		// SW = 225 +- 11.25
		else if (dir > (SW - 11.25) && dir <= (SW + 11.25)) {
			return "SW";
		}

		// WSW = 247.5 +- 11.25
		else if (dir > (WSW - 11.25) && dir <= (WSW + 11.25)) {
			return "WSW";
		}

		// W = 270 +- 11.25
		else if (dir > (W - 11.25) && dir <= (W + 11.25)) {
			return "W";
		}

		// WNW = 292.5 +- 11.25
		else if (dir > (WNW - 11.25) && dir <= (WNW + 11.25)) {
			return "WNW";
		}

		// NW = 315 +- 11.25
		else if (dir > (NW - 11.25) && dir <= (NW + 11.25)) {
			return "NW";
		}

		// NNW = 337.5 +- 11.25
		else if (dir > (NNW - 11.25) && dir <= (NNW + 11.25)) {
			return "NNW";
		}

		else
			return "error";

	}

}
