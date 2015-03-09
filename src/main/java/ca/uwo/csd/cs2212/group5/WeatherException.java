package ca.uwo.csd.cs2212.group5;

/**
 * This class extends Exception to provide a unique Exception class in order to
 * facilitate the catching and handling of errors encountered during runtime.
 * 
 * @author Team 5
 *
 */
public class WeatherException extends Exception {

	public WeatherException(String mssg) {
		super(mssg);

	}
}
