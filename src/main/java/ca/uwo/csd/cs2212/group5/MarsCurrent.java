package ca.uwo.csd.cs2212.group5;

public class MarsCurrent {

	private int minTemp;
	private int maxTemp;
	private int speed;
	private String direction;
	private int pressure;
	private int humidity;
	private String description;

	/**
	 * constructor
	 */
	public MarsCurrent() {
	}

	/**
	 * set humidity as value of parameter
	 * @param value
	 */
	public void setHumidity(int value) {
		humidity = value;
	}

	/**
	 * set wind speed as value of parameter
	 * @param value
	 */
	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		this.speed = value;
	}

	/**
	 * set wind direction as value of parameter
	 * @param dir
	 */
	public void setWindDir(String dir) {
		direction = dir;
	}

	/**
	 * set pressure as value of parameter
	 * @param value
	 */
	public void setPressure(int value) {
		pressure = value;
	}

	/**
	 * set min temp as value of parameter
	 * @param value
	 */
	public void setMinTemp(int value) {
		minTemp = value;
	}

	/**
	 * set max temp as value of parameter
	 * @param value
	 */
	public void setMaxTemp(int value) {
		maxTemp = value;
	}

	/**
	 * set condition as value of parameter
	 * @param condition
	 */
	public void setCondition(String condition) {
		System.out.println(condition);
		if ((condition.length() == 0) || (condition == null)) {
			this.description = "Condition unavailable.";
		} else
			this.description = condition;
	}

	// /////////////////

	/**
	 * return humidity
	 * @return
	 */
	public String getHumidity() {
		return Integer.toString(humidity);

	}

	/**
	 * return wind speed
	 * @return
	 */
	public String getWindSpeed() {
		if (speed != 0)
			return Integer.toString(speed);
		else
			return "";
	}

	/**
	 * return wind direction
	 * @return
	 */
	public String getWindDir() {
		return direction;
	}

	/**
	 * return pressure
	 * @return
	 */
	public String getPressure() {
		return Integer.toString(pressure);

	}

	/**
	 * return min temp
	 * @param cels
	 * @return
	 */
	public String getMinTemp(boolean cels) {

		if (cels)
			return Integer.toString(minTemp);
		else
			return Integer.toString(MiscOperations.temperatureUnit(minTemp,
					cels));

	}

	/**
	 * return max temp
	 * @param cels
	 * @return
	 */
	public String getMaxTemp(boolean cels) {
		if (cels)
			return Integer.toString(maxTemp);
		else
			return Integer.toString(MiscOperations.temperatureUnit(maxTemp,
					cels));

	}

	/**
	 * return condition
	 * @return
	 */
	public String getCondition() {
		return description;
	}

}
