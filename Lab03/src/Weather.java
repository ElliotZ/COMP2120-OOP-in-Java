/**
 * class definition of the weather class.
 * @author zhu15a
 * date: October 10th, 2019
 */
public class Weather {
	// attributes
	private int tempF;  // should be between -50 and 150
	private int windSpeed;  // should be greater than 0
	private double windChill;
	private boolean severe;
	
	// constructors
	/**
	 * default constructor. sets tempF to 0 and windSpeed to 1, then 
	 * calculate the other 2 attributes according to this.
	 */
	public Weather() {
		tempF = 0;
		windSpeed = 1;
		update();
	}
	
	/**
	 * overloaded constructor. sets tempF and windSpeed to desired value
	 * if they are valid (using the isValidTemp() and isValidWS() method),
	 * otherwise behaves exactly like the default constructor.
	 * @param tempF the tempF to be set.
	 * @param windSpeed the windSpeed to be set.
	 */
	public Weather(int tempF, int windSpeed) {
		this();
		if (isValidTemp(tempF) && isValidWS(windSpeed)) {
			setTempF(tempF);
			setWindSpeed(windSpeed);
		}
		update();
	}
	
	// helper methods
	/**
	 * use getWindChill() to calculate current windChill, then determine whether
	 * it is severe or not.
	 * @return true if update is successful.
	 */
	private boolean update() {
		windChill = getWindChill();
		severe = (windChill < -15)? true : false;
		return true;
	}
	
	/**
	 * @param tempF the tempF to be checked.
	 * @return true if tempF is between 150 and 50 inclusive. false otherwise.
	 */
	private boolean isValidTemp(int tempF) {
		return (tempF <= 150 && tempF >= 50);
	}
	
	/**
	 * @param windSpeed the windSpeed to be checked.
	 * @return true if windSpeed is larger than 0. false otherwise.
	 */
	private boolean isValidWS(int windSpeed) {
		return (windSpeed > 0);
	}
	
	// methods
	/**
	 * @return the tempF
	 */
	public int getTempF() {
		return tempF;
	}

	/**
	 * only sets the tempF if it is valid. otherwise leaves it untouched.
	 * also updates the value of windChill and severe.
	 * @param tempF the tempF to set
	 * @return the current value of this.tempF.
	 */
	public int setTempF(int tempF) {
		if (isValidTemp(tempF)) this.tempF = tempF;
		update();
		return this.tempF;
	}

	/**
	 * @return the windSpeed
	 */
	public int getWindSpeed() {
		return windSpeed;
	}

	/**
	 * only sets the windSpeed if it is valid. otherwise leaves it untouched.
	 * also updates the value of windChill and severe.
	 * @param windSpeed the windSpeed to set
	 * @return the current value of this.windSpeed.
	 */
	public int setWindSpeed(int windSpeed) {
		if (isValidWS(windSpeed)) this.windSpeed = windSpeed;
		update();
		return this.windSpeed;
	}
	
	/**
	 * @return windChill, calculated using the North American formula, rounded to 1 decimal digit.
	 */
	public double getWindChill() {
		double windChill = 35.74 + 0.6215 * tempF
				           - 35.75 * Math.pow(windSpeed, 0.16) 
		                   + 0.4275 * tempF * Math.pow(windSpeed, 0.16);
		return (Math.round(windChill) * 10) / 10.0;
	}
	
	/**
	 * @return true if the weather is severe. false otherwise.
	 */
	public boolean isSevere() {
		return severe;
	}
	
	/**
	 * overrides Object.toString().
	 * @return a string containing the tempF, windChill and windSpeed information of the current object.
	 */
	public String toString() {
		return "Current Temperature is " + tempF + " \'F. Feels like " 
	                       + this.getWindChill() + " \'F and Wind Speed is " + windSpeed + " mph.";
	}


}
