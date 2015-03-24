package ca.uwo.csd.cs2212.group5;

public class ShortTerm {

	private Parameter temperature;
	private String description;
	
	public ShortTerm(){
		
	}

	public void setTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		temperature = new Parameter("Temperature", "Celsius", value);

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return (temperature.toString() + "\n" + "Conditions: \t\t" + description);
	}
	
}
