/**
 * @author zhu15a
 *
 */
public class Rectangle extends Shape {
	// attributes
	private double width;
	private double height;

	// constructors
	/**
	 * default constructor
	 */
	public Rectangle() {
		super.setShape("Rectangle");
		width = 0.0;
		height = 0.0;
	}
	
	/**
	 * overloaded constructor
	 * @param width the width to be set
	 * @param height the height to be set
	 */
	public Rectangle(double width, double height) {
		this();
		setWidth(width);
		setHeight(height);
	}
	
	// method
	/**
	 * @param width the width to be set
	 * @return the current value of this.width
	 */
	public double setWidth(double width) {
		if (isValidLength(width)) {
			this.width = width;
		}
		return this.width;
	}
	
	/**
	 * @param height the height to be set
	 * @return the current value of this.height
	 */
	public double setHeight(double height) {
		if (isValidLength(height)) {
			this.height = height;
		}
		return this.height;
	}

	/* (non-Javadoc)
	 * @see Shape#CalculateArea()
	 */
	@Override
	public double CalculateArea() {
		return width * height;
	}
	
}