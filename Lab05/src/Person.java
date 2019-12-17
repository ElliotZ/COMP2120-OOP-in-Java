/**
 * Class definition of the class Person.
 * @author zhu15a
 * Date: Oct 20, 2019
 */
public class Person {
	// attributes
	private String name;  // non-empty
	private int age;  // between 1 and 149 inclusive.
	
	// constructor
	/**
	 * default constructor
	 */
	public Person() {
		this.name = "private constructor";
		this.age = 18;
	}
	
	/**
	 * overloaded constructor.
	 * @param name a string that is the name of this person.
	 * @param age an integer between 1 and 149.
	 */
	public Person(String name, int age) {
		this();
		if (isValidName(name) && isValidAge(age)) {
			this.name = name;
			this.age = age;
		}
	}
	
	// helper methods
	/**
	 * @param name an arbitrary string.
	 * @return true if name is non-empty. false otherwise.
	 */
	public boolean isValidName(String name) {
		return (!name.isEmpty());
	}
	
	/**
	 * @param age an arbitrary integer.
	 * @return true if age is between 1 and 149 inclusive. false otherwise.
	 */
	public boolean isValidAge(int age) {
		return (age > 0 && age < 150);
	}
	
	// methods
	/**
	 * if parameter is not a valid name, does nothing.
	 * @param name the name to be set.
	 * @return current this.name.
	 */
	public String setName(String name) {
		if (isValidName(name)) {
			this.name = name;
		}
		return this.name;
	}
	
	/**
	 * if parameter is not a valid age, does nothing.
	 * @param age the age to be set.
	 * @return current this.age.
	 */
	public int setAge(int age) {
		if (isValidAge(age)) {
			this.age = age;
		}
		return this.age;
	}
	
	/**
	 * @return current name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return current age
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * overrides Object.toString().
	 * @return a string that contains the name and age of this Person.
	 */
	public String toString() {
		return getName() + " is " + getAge() + " years old.";
	}

}
