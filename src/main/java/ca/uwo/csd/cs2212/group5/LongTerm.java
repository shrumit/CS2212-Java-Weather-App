package ca.uwo.csd.cs2212.group5;

public class LongTerm {

	private Parameter temperature;
	private String description;
	private Parameter minTemp;
	private Parameter maxTemp;
	private static final Parameter nullParam = new Parameter("Error", "Error", 0);
	
	
	public LongTerm(){
		
	}

	public void setTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		temperature = new Parameter("Temperature", "Celsius", value);

	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setMinTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		minTemp = new Parameter("Min Temp", "Celsius", value);
	}
	
	public void setMaxTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		minTemp = new Parameter("Max Temp", "Celsius", value);
	}



	public String toString() {
		return (temperature.toString() + "\n" + "Conditions: \t\t" + description);
	}

}
