import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author zhu15a
 * Date: Nov 6, 2019
 */
public class DrawingApp {

	public static void main(String[] args) {
		Shape[] arr = new Shape[10];
		Random rand = new Random();
		NumberFormat formatter = new DecimalFormat("#0.00");
		double totalArea = 0.0;
		
		// populating the array
		for (int i = 0; i < 10; ++i) {
			// randomize shape and size
			if (rand.nextInt(3) == 0) {
				arr[i] = new Circle(rand.nextDouble() * 20.0 + 0.01);
			} else if (rand.nextInt(3) == 1) {
				arr[i] = new Rectangle(rand.nextDouble() * 20.0 + 0.01, rand.nextDouble() * 20.0 + 0.01);
			} else {
				arr[i] = new Triangle(rand.nextDouble() * 20.0 + 0.01, rand.nextDouble() * 20.0 + 0.01);
			}
		}
		
		// print shape
		for (int i = 0; i < 10; ++i) {
			System.out.println(arr[i]);
			totalArea += arr[i].CalculateArea();
		}
		
		// total area
		System.out.println("The total area of the 10 shapes is " + formatter.format(totalArea));

	}

}
