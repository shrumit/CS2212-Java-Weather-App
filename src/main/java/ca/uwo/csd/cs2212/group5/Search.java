package ca.uwo.csd.cs2212.group5;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;

public class Search {

	private City[] api;
	private final String url1 = "http://api.openweathermap.org/data/2.5/find?q=";
	private final String url2 = "&type=like&mode=json";
	private int count;

	public Search(String arg) {
		String jsonString = MiscOperations.readFromURL(url1 + arg + url2);

		JSONObject main = new JSONObject(jsonString);
		JSONArray array = main.getJSONArray("list");

		count = (int) main.get("count");
		api = new City[count];

		for (int i = 0; i < count; i++) {
			JSONObject location = array.getJSONObject(i);
			int id = (int) location.get("id");
			String city = location.getString("name");
			String country = location.getJSONObject("sys").getString("country");

			api[i] = new City(city, country, id);
		}

	}

	public Iterator getResults() {
		ArrayList al = new ArrayList();

		if (count == 0) {
			al.add("No results found.");
		} else {

			for (int i = 0; i < count; i++)
				al.add(api[i].toString());
		}
		return al.iterator();
	}

	public int getId(int index) {
		return 2131;
	}

}