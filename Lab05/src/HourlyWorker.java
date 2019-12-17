import java.text.*;

/**
 * Class definition of the HourlyWorker class. Extends the Employee class.
 * @author zhu15a
 * Date: Oct 24, 2019
 */
public class HourlyWorker extends Employee {
	// attributes
	private int num_hours;  // non-negative integer, number of hours per week. should also be less than 168 (24 * 7).
	private double hourly_rate;  // positive double

	// constructor
	/**
	 * default constructor. sets num_hour to 1 and hourly_rate to 0.01.
	 */
	public HourlyWorker() {
		this.num_hours = 1;
		this.hourly_rate = 0.01;
	}

	/**
	 * overloaded constructor. invokes super class constructor to set name, age and year_hired.
	 * allows the user to set valid num_hours and hourly_rate. if the values are not valid, it works as if the default constructor is called.
	 */
	public HourlyWorker(String name, int age, int year_hired, int num_hours, double hourly_rate) {
		super(name, age, year_hired);
		this.num_hours = 1;
		this.hourly_rate = 0.01;
		if (isValidHours(num_hours) && isValidRate(hourly_rate)) {
			this.num_hours = num_hours;
			this.hourly_rate = hourly_rate;
		}
	}

	// helper methods
	/**
	 * @param num_hours the number of hours this worker has worked this week.
	 * @return true if num_hours is between 0 and 168 non-inclusive. (168 being the number of hours of a week.) false otherwise.
	 */
	public boolean isValidHours(int num_hours) {
		return (num_hours > 0 && num_hours < 168);
	}

	/**
	 * @param hourly_rate the hourly wage for this worker.
	 * @return true if hourly_rate is positive.
	 */
	public boolean isValidRate(double hourly_rate) {
		return (hourly_rate > 0);
	}

	// methods
	/**
	 * you can only set hours to be larger than 0 with this method, even though hours can technically be 0.
	 * the only time you can set hours to 0 is when you pay the worker.
	 * @param num_hours the num_hours to be set.
	 * @return current value of this.num_hours.
	 */
	public int setNumHours(int num_hours) {
		if (isValidHours(num_hours)) {
			this.num_hours = num_hours;
			super.resetPaid();
		}
		return this.num_hours;
	}

	/**
	 * @param hourly_rate the hourly_rate to be set.
	 * @return current value of this.hourly_rate.
	 */
	public double setHourlyRate(double hourly_rate) {
		if (isValidRate(hourly_rate)) {
			this.hourly_rate = hourly_rate;
		}
		return this.hourly_rate;
	}

	/**
	 * @return current value of this.num_hours.
	 */
	public int getNumHours() {
		return this.num_hours;
	}

	/**
	 * @return current value of this.hourly_rate.
	 */
	public double getHourlyRates() {
		return this.hourly_rate;
	}

	/**
	 * calculates the due payment for this worker this week.
	 * @return hours * rates.
	 */
	public double getPayment() {
		return getNumHours() * getHourlyRates();
	}

	/**
	 * pays the worker if the worker has worked this week (i.e. hours is not 0). does nothing otherwise.
	 * once a worker is paid, his hours is reset to 0.
	 * @return a string that indicates the outcome of this method, i.e. if the worker is paid or not, and the amount paid if he is paid.
	 */
	public String payWorker() {
		String ret;
		if (!super.payWorker().isEmpty()) {
			NumberFormat formatter = new DecimalFormat("#0.00");
			ret = getName() + " is an hourly Worker and is paid " + formatter.format(getPayment());
			System.out.println(ret);
			this.num_hours = 0;
			return ret;
		} else {
			ret = getName() + " has already been paid and needs to work more hours this week.";
			System.out.println(ret);
			return ret;
		}
	}

	// a toString() method is not implemented as there is no need to override the toString() in the super class (Employee).

}
