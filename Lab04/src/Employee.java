import java.util.Calendar;

/**
 * Class definition of the Employee class, which extends the Person class.
 * @author zhu15a
 * Date: Oct 20, 2019
 */
public class Employee extends Person {
	// attributes
	private int year_hired;  // between 1900 and 2999
	private int id;
	private static int counter = 0;
	
	// constructors
	/**
	 * default constructor.
	 */
	public Employee() {
		this.year_hired = Calendar.getInstance().get(Calendar.YEAR);
		this.id = ++counter;
	}
	
	/**
	 * overloaded constructor. falls back to default constructor if input is illegal.
	 * @param name an arbitrary string.
	 * @param age an arbitrary integer.
	 * @param year_hired an arbitrary integer.
	 */
	public Employee(String name, int age, int year_hired) {
		super(name, age);
		this.year_hired = Calendar.getInstance().get(Calendar.YEAR);
		this.id = ++counter;
		if (isValidYear(year_hired)) {
			this.year_hired = year_hired;
		}
	}
	
	// helper methods
	/**
	 * @param year_hired an arbitrary integer.
	 * @return true if year_hired is between 1900 and 2999, false otherwise.
	 */
	public boolean isValidYear(int year_hired) {
		return (year_hired > 1900 && year_hired < 2999);
	}
	
	// methods
	/**
	 * @param year_hired the year_hired to be set.
	 * @return current year_hired.
	 */
	public int setYearHired(int year_hired) {
		if (isValidYear(year_hired)) {
			this.year_hired = year_hired;
		}
		return this.year_hired;
	}
	
	/**
	 * @return current year_hired.
	 */
	public int getYearHired() {
		return this.year_hired;
	}
	
	/**
	 * @return current id.
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * overrides Object.toString().
	 * @return a String containing the name, year_hired and age of the object.
	 */
	public String toString() {
		return getName() + " was hired in " + getYearHired() + " and is " + getAge() + " years old.";
	}
}
