package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Takes a cityId then fetches the current weather, forecast by
 * day and forecast by 3-hour increments from the OWM server in JSON format. The
 * JSON string is then parsed and the pertinent data is stored in one object of
 * CurrentWeather, 5 objects of LongTerm (ie 5 days daily forecast) and 8
 * objects of ShortTerm (ie 8*3 = 24 hour forecast).
 * 
 * @author CS2212 Team 5
 *
 */
public class Location {

	private double longitude;
	private double lattitude;
	private String timezone;
	private String currentJson;
	private String longJson;
	private String shortJson;
	private int cityId;

	private Calendar refreshTime;

	public CurrentWeather cw;
	public LongTerm[] lt = new LongTerm[5];
	public ShortTerm[] st = new ShortTerm[8];

	private static final String urlPrefix = "http://api.openweathermap.org/data/2.5";
	private static final String currentPrefix = "/weather?id=";
	private static final String stermPrefix = "/forecast?id=";
	private static final String ltermPrefix = "/forecast/daily?id=";
	private static final String urlSuffix = "&mode=json";

	/**
	 * Constructor that takes the city id, gets the timezone, stores the current
	 * (refresh) time and calls other methods. It then calls makeJsons(),
	 * setCoordinates(), makeLongTerm(), makeShortTerm(), and make Current()
	 * 
	 * @param cityId
	 *            is the identifier used by OWM for a particular city
	 */
	public Location(int cityId) {
		this.cityId = cityId;
		makeJsons();
		if ((currentJson.length() == 0) || (longJson.length() == 0)
				|| (shortJson.length() == 0))
			makeJsons();

		setCoordinates();

		// Initialize timezone of city
		timezone = MiscOperations.determineTimezone(lattitude, longitude);
		if (timezone.equalsIgnoreCase("error")) {
			timezone = "America/Toronto";
		}

		// Initialize refresh time to current time
		refreshTime = Calendar.getInstance();

		makeLongTerm();
		makeShortTerm();
		makeCurrent();
	}

	/**
	 * Gets JSON from the OWM api using the stored prefixes and stores it all in
	 * strings.
	 */
	private void makeJsons() {
		currentJson = MiscOperations.readFromURL(urlPrefix + currentPrefix
				+ cityId + urlSuffix);
		longJson = MiscOperations.readFromURL(urlPrefix + ltermPrefix + cityId
				+ urlSuffix);
		shortJson = MiscOperations.readFromURL(urlPrefix + stermPrefix + cityId
				+ urlSuffix);

	}

	/**
	 * Determines the coordinates of this city from the JSON. These are used to
	 * extract the timezone.
	 */
	private void setCoordinates() {
		JSONObject entire = new JSONObject(currentJson);
		JSONObject coord = entire.getJSONObject("coord");

		longitude = Double.parseDouble(coord.get("lon").toString());
		lattitude = Double.parseDouble(coord.get("lat").toString());

	}

	/**
	 * Parses through JSON for the long term weather and makes 5 objects of the
	 * LongTerm class containing weather data.
	 */
	private void makeLongTerm() {
		JSONObject entire = new JSONObject(longJson);
		JSONArray listArray = entire.getJSONArray("list");

		for (int i = 0; i < 5; i++) {
			JSONObject thisInstance = listArray.getJSONObject(i);
			JSONObject weather = thisInstance.getJSONArray("weather")
					.getJSONObject(0);
			JSONObject temp = thisInstance.getJSONObject("temp");

			lt[i] = new LongTerm(timezone);

			// Unix Time
			lt[i].setTime(Long.parseLong(thisInstance.get("dt").toString()));

			// Temperature
			int tempInt = (int) Double.parseDouble(temp.get("day").toString());
			lt[i].setTemp(tempInt);

			// Condition Description
			lt[i].setDescription(weather.get("description").toString());

			// Minimum Temperature
			tempInt = (int) Double.parseDouble(temp.get("min").toString());
			lt[i].setMinTemp(tempInt);

			// Maximum Temperature
			tempInt = (int) Double.parseDouble(temp.get("max").toString());
			lt[i].setMaxTemp(tempInt);

			// Icon Code
			lt[i].setIcon(weather.get("icon").toString());
		}
	}

	/**
	 * Parses through JSON for the short term weather and makes 8 objects of the
	 * ShortTerm class containing weather data.
	 */
	private void makeShortTerm() {

		JSONObject entire = new JSONObject(shortJson);
		JSONArray listArray = entire.getJSONArray("list");

		for (int i = 0; i < 8; i++) {
			JSONObject thisInstance = listArray.getJSONObject(i);
			JSONObject main = thisInstance.getJSONObject("main");
			JSONObject weather = thisInstance.getJSONArray("weather")
					.getJSONObject(0);

			st[i] = new ShortTerm(timezone);

			// Unix Time
			st[i].setTime(Long.parseLong(thisInstance.get("dt").toString()));

			// Temperature
			int tempInt = (int) Double.parseDouble(main.get("temp").toString());
			st[i].setTemp(tempInt);

			// Condition Description
			st[i].setDescription(weather.get("description").toString());

			// Icon Code
			st[i].setIcon(weather.get("icon").toString());
		}

	}

	/**
	 * Parses through JSON for the current weather and makes an object of the
	 * CurrentWeather class containing weather data.
	 */
	private void makeCurrent() {

		int tempInt; // intermediate variable for casting double (given by api)

		cw = new CurrentWeather(timezone);

		JSONObject entire = new JSONObject(currentJson);
		JSONObject sys = entire.getJSONObject("sys");
		JSONObject weather = entire.getJSONArray("weather").getJSONObject(0);
		JSONObject main = entire.getJSONObject("main");
		JSONObject wind = entire.getJSONObject("wind");

		// Temperature
		tempInt = (int) Double.parseDouble(main.get("temp").toString());
		cw.setTemperature(tempInt);

		// Minimum Temperature
		tempInt = (int) Double.parseDouble(main.get("temp_min").toString());
		cw.setMinTemp(tempInt);

		// Maximum Temperature
		tempInt = (int) Double.parseDouble(main.get("temp_max").toString());
		cw.setMaxTemp(tempInt);

		// Humidity
		tempInt = (int) Double.parseDouble(main.get("humidity").toString());
		cw.setHumidity(tempInt);

		// Air Pressure
		tempInt = (int) Double.parseDouble(main.get("pressure").toString());
		cw.setPressure(tempInt);

		// Wind Speed
		tempInt = (int) Double.parseDouble(wind.get("speed").toString());
		cw.setWindSpeed(tempInt);

		// Wind Direction
		tempInt = (int) Double.parseDouble(wind.get("deg").toString());
		cw.setWindDir(tempInt);

		// Condition Description
		cw.setDescription(weather.get("description").toString());

		cw.setIconCode(weather.get("icon").toString());

		// Sunset Time
		cw.setSunset(Long.parseLong(sys.get("sunset").toString()));

		// Sunrise Time
		cw.setSunrise(Long.parseLong(sys.get("sunrise").toString()));

	}

	/**
	 * Returns a string containing the refresh time (ie when the contructor was
	 * called).
	 * 
	 * @return a String showing time formatted as "dd MMM hh:MM a"
	 */
	public String getRefreshTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM hh:mm a");
		sdf.setTimeZone(refreshTime.getTimeZone());
		return sdf.format(refreshTime.getTime());
	}
}
