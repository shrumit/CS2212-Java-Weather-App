package ca.uwo.csd.cs2212.group5;

public class CurrentWeather {

	private Parameter temperature;
	private Parameter humidity;
	private Parameter windSpeed;
	private Parameter windDirection;
	private Parameter airPressure;
	private Parameter minTemp;
	private Parameter maxTemp;
	private Time riseTime;
	private Time setTime;
	private Time time;
	
	public CurrentWeather() {

	}

	public void setTemperature(double value) {
		value = MiscOperations.tempToCelsius(value);
		temperature = new Parameter("Temperature", "Celsius", value);
	}

	public void setHumidity(int value) {
		humidity = new Parameter("Humidity", "%", value);
	}

	public void setWindSpeed(double value) {
		value = MiscOperations.windConvert(value);
		windSpeed = new Parameter("Wind Speed", "km/h", value);
	}

	public void setPressure(int value) {
		airPressure = new Parameter("Air Pressure", "hPa", value);
	}

	public void setMinTemp(double value) {
		value = MiscOperations.tempToCelsius(value);
		minTemp = new Parameter("Min Temp", "Celsius", value);
	}

	public void setMaxTemp(double value) {
		value = MiscOperations.tempToCelsius(value);
		maxTemp = new Parameter("Max Temp", "Celsius", value);
	}
	
	public void setWindDir(int value) {
		String dir = MiscOperations.convertWindDir(value);
		windDirection = new Parameter ("Wind Direction", dir);
	}

	public String toString() {
		return (temperature.toString() + "\n" + humidity.toString() + "\n"
				+ windSpeed.toString() + "\n" + windDirection.toString() + "\n"
				+ airPressure.toString() + "\n" + minTemp.toString() + "\n" + maxTemp
					.toString());

	}
}
