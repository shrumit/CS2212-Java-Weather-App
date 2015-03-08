/**
 * 
 */

package ca.uwo.csd.cs2212.group5;

public class MiscOperations {
	
	// for the conversion of wind directions
	private double N = 0;
	private double NNE = 22.5;
	private double NE = 45;
	private double ENE = 67.5;
	private double E = 90;
	private double ESE = 112.5;
	private double SE = 135;
	private double SSE = 157.5;
	private double S = 180;
	private double SSW = 202.5;
	private double SW = 225;
	private double WSW = 247.5;
	private double W = 270;
	private double WNW = 292.5;
	private double NW = 315;
	private double NNW = 338;

	// converts windspeed from m/s to km/h
	public static double windConvert(double mps) {
		return mps * 3.6;
	}

	// converts from kelvin to celsius
	public static int tempToCelsius(int kelvin) {
		return (int) (kelvin - 273.15);
	}
	
	// converts a direction double to a string value indicating the direction
	public static String convertWindDir(double dir){
		//N = 0/360 +- 11.25
		if (dir >= (NNW + 11.25) && dir <= 360 || dir >= 0 && dir <= (NNE - 11.25){
			return "N";
		}
		
		//NNE = 22.5 +- 11.25
		else if (dir > (NNE - 11.25) && dir <= (NNE + 11.25)) {
			return "NNE";
		} 
		
		//NE = 45 +- 11.25
		else if (dir > (NE - 11.25) && dir <= (NE + 11.25)) {
			return "NE";
		} 
		
		//ENE = 67.5 +- 11.25
		else if (dir > (ENE - 11.25) && dir <= (ESE + 11.25) {
			return "ENE";
		} 
		
		//E = 90 +- 11.25
		else if (dir > (E - 11.25) && dir <= (E + 11.25) {
			return "E";
		} 
		
		//ESE = 112.5 +- 11.25
		else if (dir > (ESE - 11.25) && dir <= (ESE + 11.25) {
			return "ESE";
		}
		
		//SE = 135 +- 11.25
		else if (dir > (SE - 11.25) && dir <= (SE + 11.25) {
			return "SE";
		} 
		
		//SSE = 157.5 +- 11.25
		else if (dir > (SSE - 11.25) && dir <= (SSE + 11.25) {
			return "SSE";
		} 
		
		//S = 180 +- 11.25
		else if (dir > (S - 11.25) && dir <= (S + 11.25) {
			return "S";
		} 
		
		
		//SSW = 202.5 +- 11.25
		else if (dir > (SSW - 11.25) && dir <= (SSW + 11.25) {
			return "SSW";
		} 
		
		//SW = 225 +- 11.25
		else if (dir > (SW - 11.25) && dir <= (SW +11.25) {
			return "SW";
		} 
		
		//WSW = 247.5 +- 11.25
		else if (dir > (WSW - 11.25) && dir <= (WSW + 11.25) {
			return "WSW";
		} 
		
		//W = 270 +- 11.25
		else if (dir > (W - 11.25) && dir <= (W + 11.25) {
			return "W";
		} 
		
		//WNW = 292.5 +- 11.25
		else if (dir > (WNW - 11.25) && dir <= (WNW + 11.25) {
			return "WNW";
		} 
		
		//NW = 315 +- 11.25
		else if (dir > (NW - 11.25) && dir <= (NW + 11.25) {
			return "NW";
		} 
	
		//NNW = 337.5 +- 11.25
		else if (dir > (NNW - 11.25) && dir <= (NNW + 11.25) {
			return "NNW";
		} 
	}
}
