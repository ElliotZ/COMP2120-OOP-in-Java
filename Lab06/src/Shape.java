import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class definition of the abstract class Shape.
 * @author zhu15a
 * Date: Nov 6, 2019
 */
public abstract class Shape {
	// attributes
	private int id;
	private String label;
	private static int counter = 0;
	
	// constructors
	/**
	 * default constructor
	 */
	public Shape() {
		id = counter++;
		// label = "";
	}
	
	// helper methods
	/**
	 * @param length a double.
	 * @return true if length is positive, false otherwise.
	 */
	public boolean isValidLength(double length) {
		return length > 0;
	}
	
	// abstract methods
	/**
	 * the formula used to calculate the area for different shapes is different, 
	 * thus is implemented in sub classes.
	 * @return the area of the shape.
	 */
	public abstract double CalculateArea();
	
	// methods
	/**
	 * @param shape the shape label to be set
	 * @return current value of this.label
	 */
	protected String setShape(String shape) {
		this.label = shape;
		return this.label;
	}
	
	/**
	 * @return current value of this.id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return current value of this.label
	 */
	public String getShape() {
		return this.label;
	}
	
	/**
	 * @return a string that contains the exact shape, the unique id and the area of this shape.
	 */
	public String toString() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return "Shape id: " + getId() + " is a " + getShape() +
				".\nIts Area is " + formatter.format(this.CalculateArea()) + ".";
	}

}
