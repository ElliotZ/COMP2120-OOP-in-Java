import java.util.*;

/**
 * Class definition of the TestHourlyWorker class.
 * @author zhu15a
 * Date: Oct 24, 2019
 */
public class TestHourlyWorker {
	public static void main (String[] args) {
		// name, age, date hired, number of hours, hourly rate
		HourlyWorker hw1 = new HourlyWorker("Jim", 19, 2017, 10, 15.75);
		int numTestCases = 5;  // number of test cases

		System.out.println(hw1);

		hw1.payWorker();
		System.out.println();
		hw1.payWorker();

		System.out.println();
		System.out.println("===================================================");
		System.out.println();

		// use random numbers to generate random workers, and their hours and rates
		Random randnum = new Random();
		Employee[] payroll = new Employee[26];  // capacity is 26 for now since we don't have more names.
		// raw name table
		String[] names = {"Alice", "Becca", "Caitlyn", "Debbie", "Emma",
				          "Fatemah", "Geri", "Hailie", "Isabelle", "Jane",
				          "Kate", "Linda", "Mary", "Neptune", "Oprah",
				          "Penny", "Queenie", "Robyn", "Samantha", "Tara",
				          "Ursa", "Vira", "Wendy", "Xandra", "Yvonne", "Zendaya"};

		// prepare a LinkedList for randomizing names.
		LinkedList<String> namelist = new LinkedList<String>();

		// shuffle the LinkedList
		for (String name : names) {
			if (randnum.nextInt() > 0) {
				namelist.addFirst(name);
			} else {
				namelist.addLast(name);
			}
		}

		// randomly remove some names
		for (int i = 0; i < (26 - numTestCases); ++i) {
			namelist.remove(randnum.nextInt(26 - i));
		}

		// test with random names and random data. each name will only be used once.
		for (int i = 0; i < numTestCases; ++i) {
			payroll[i] = new HourlyWorker(namelist.pollLast(),
					                      randnum.nextInt(5) + 18,
					                      randnum.nextInt(5) + 2015,
					                      randnum.nextInt(40) + 1,  // randnum.nextInt(168) + 1 for full range
					                      randnum.nextDouble() * 10 + 10.0);  // + 0.01 for full rage
			System.out.println(payroll[i]);
			payroll[i].payWorker();
			System.out.println();
		}
	}

}
