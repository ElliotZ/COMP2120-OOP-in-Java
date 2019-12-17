/**
 * class definition of the WeatherTester class.
 * @author zhu15a
 * Date: October 10th, 2019
 */
public class WeatherTester {
	public static void main(String[] args) {
		// instantiate 2 objects of weather
		Weather w1 = new Weather();
		Weather w2 = new Weather(72, 10);
		
		// print them
		System.out.println(w1);
		System.out.println(w2);
		
		// set w1
		w1.setTempF(64);
		w1.setWindSpeed(10);
		
		// test getWindChill() and isSevere().
		System.out.println(w1);
		System.out.println("Now the Wind Chill of w1 is " + w1.getWindChill() + ".");
		if (w1.isSevere()) {
			System.out.println("This is severe.");
		} else {
			System.out.println("This is not severe.");
		}
	}

}
