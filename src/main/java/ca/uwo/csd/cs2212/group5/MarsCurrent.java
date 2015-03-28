package ca.uwo.csd.cs2212.group5;

public class MarsCurrent {

	private int minTemp;
	private int maxTemp;
	private int speed;
	private String direction;
	private int pressure;
	private int humidity;
	private String description;

	public MarsCurrent() {
	}

	public void setHumidity(int value) {
		humidity = value;
	}

	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		this.speed = value;
	}

	public void setWindDir(String dir) {
		direction = dir;
	}

	public void setPressure(int value) {
		pressure = value;
	}

	public void setMinTemp(int value) {
		minTemp = value;
	}

	public void setMaxTemp(int value) {
		maxTemp = value;
	}

	public void setCondition(String condition) {
		this.description = condition;
	}

	// /////////////////

	public String getHumidity() {
		return Integer.toString(humidity);

	}

	public String getWindSpeed() {
		return Integer.toString(speed);
	}

	public String getWindDir() {
		return direction;
	}

	public String getPressure() {
		return Integer.toString(pressure);

	}

	public String getMinTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(minTemp, cels);
		return Integer.toString(tempInt);

	}

	public String getMaxTemp(boolean cels) {
		int tempInt = MiscOperations.temperatureUnit(maxTemp, cels);
		return Integer.toString(tempInt);

	}

	public String getCondition() {
		return description;
	}

}
