package ca.uwo.csd.cs2212.group5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONObject;

/**
 * Static class that holds methods that are common to various classes throughout
 * the project.
 * 
 * @author CS2212 Team 5
 *
 */
public class MiscOperations {

	private static final double NNE = 22.5;
	private static final double NE = 45;
	private static final double ENE = 67.5;
	private static final double E = 90;
	private static final double ESE = 112.5;
	private static final double SE = 135;
	private static final double SSE = 157.5;
	private static final double S = 180;
	private static final double SSW = 202.5;
	private static final double SW = 225;
	private static final double WSW = 247.5;
	private static final double W = 270;
	private static final double WNW = 292.5;
	private static final double NW = 315;
	private static final double NNW = 338;

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
	 *            temperature in Kelvin
	 * @return the temperature in Celsius
	 */
	public static int getCelsius(int kelvin) {
		return (int) (kelvin - 273.15);
	}

	/**
	 * Converts temperature from Kelvin to Farhenheit
	 * 
	 * @param kelvin
	 *            temperature in Kelvin
	 * @return the temperature in Farhenheit
	 */
	public static int getFarhenheit(int kelvin) {
		return (int) ((getCelsius(kelvin) * 9) / 5) + 32;
	}

	/**
	 * Converts temperature from Kelvin to the unit specified by the boolean.
	 * 
	 * @param kelvin
	 *            temperature in Kelvin
	 * @param unit
	 *            Celsius if true, Farhenheit if false
	 * @return temperature in Farhenheit in the desired unit
	 */
	public static int temperatureUnit(int kelvin, boolean unit) {
		if (unit)
			return (getCelsius(kelvin));
		else
			return (getFarhenheit(kelvin));
	}

	private static final String timezoneUrl1 = "https://maps.googleapis.com/maps/api/timezone/json?location=";
	private static final String timezoneUrl2 = "&timestamp=0";

	/**
	 * Determines the timezone of this city based on longitude and lattitude.
	 * Uses Google's Timezone API. The local timezone is needed in order to
	 * properly display (local) sunset/sunrise time.
	 * 
	 * @param latti
	 *            lattitude of location
	 * @param longi
	 *            longitude of location
	 * @return String timezone as per the IANA TZ database
	 * 
	 */
	public static String determineTimezone(double latti, double longi) {

		StringBuilder url = new StringBuilder();
		url.append(timezoneUrl1);
		url.append(latti);
		url.append(",");
		url.append(longi);
		url.append(timezoneUrl2);

		StringBuilder jsonText = new StringBuilder();

		try {
			URL tzone = new URL(url.toString());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					tzone.openStream()));

			String temp = "";
			while ((temp = in.readLine()) != null) {
				jsonText.append(temp);
			}
			in.close();
		} catch (Exception e) {
			return ("error");
		}
		JSONObject tzJson;

		tzJson = new JSONObject(jsonText.toString());
		return tzJson.getString("timeZoneId");
	}

	/**
	 * Returns all the String at the given URL. This method is used to fetch the
	 * raw JSON String from the supplied URL
	 * 
	 * @param url
	 *            the url from which JSON is to be fetched
	 * @return returns all the text on the supplied url webpage
	 */
	public static String readFromURL(String url) {
		StringBuilder jsonDerulo = new StringBuilder();

		try {
			URL oracle = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					oracle.openStream()));
			String temp = "";

			while ((temp = in.readLine()) != null) {
				jsonDerulo.append(temp);
			}

			in.close();

		} catch (Exception e) {
		}

		return jsonDerulo.toString();
	}

	/**
	 * Converts direction that is in degrees to a one-,two-, or three-letter
	 * string compass representation.
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
