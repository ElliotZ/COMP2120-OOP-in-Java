/**
 * @author zhu15a
 *
 */
public class Record implements Comparable<Record> {
	// attributes
	private String first_name;
	private String last_name;
	private String date_of_birth;
	
	// constructor
	/**
	 * limits not set for the purpose.			
	 */
	public Record() {
		this.first_name = "";
		this.last_name = "";
		this.date_of_birth = "";
	}
	
	/**
	 * overloaded construcor. lets you set the params.
	 * @param first_name
	 * @param last_name
	 * @param date_of_birth
	 */
	public Record(String first_name, String last_name, String date_of_birth) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
	}
	
	// methods
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the date_of_birth
	 */
	public String getDate_of_birth() {
		return date_of_birth;
	}

	/**
	 * @param date_of_birth the date_of_birth to set
	 */
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	/**
	 * prints each field delimited bt " ,",
	 */
	public String toString() {
		return this.getFirst_name() + ", " + this.getLast_name() + ", " + this.getDate_of_birth();
	}
	
	/**
	 * compare last name; if last name is the same then compare first name/
	 */
	@Override
	public int compareTo(Record o) {
		if (this.getLast_name().compareTo(o.getLast_name()) == 0) {
			return this.getFirst_name().compareTo(o.getFirst_name());
		} else {
			return this.getLast_name().compareTo(o.getLast_name());
		}
	}

}
