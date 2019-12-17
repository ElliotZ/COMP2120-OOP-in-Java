/**
 * @author zhu15a
 *
 */
public class Circle extends Shape {
	// attributes
	private double radius;

	// constructors
	/**
	 * default constructor
	 */
	public Circle() {
		super.setShape("Circle");
		radius = 0.0;
	}
	
	/**
	 * overloaded constructor
	 * @param radius the radius to be set
	 */
	public Circle(double radius) {
		this();
		setRadius(radius);
	}
	
	// method
	/**
	 * @param radius the radius to be set
	 * @return current value of this.radius
	 */
	public double setRadius(double radius) {
		if (isValidLength(radius)) {
			this.radius = radius;
		}
		return this.radius;
	}

	/* (non-Javadoc)
	 * @see Shape#CalculateArea()
	 */
	@Override
	public double CalculateArea() {
		return Math.PI * radius * radius;
	}
	
}
