/**
 * Class WeightNode. A simple Comparable data structure to help the AI player manage the weight
 * of game blocks. <br><br>
 * The getters and setters for the fields in this class are omitted, since the fields
 * don't require validation, and are not intended for extensive uses.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public class WeightNode implements Comparable<WeightNode> {
	// attributes
	public int weight;
	public int opposite_weight;
	public int x;
	public int y;
	
	// constructors
	/**
	 * default constructor.
	 */
	public WeightNode() {
		weight = -1;
		opposite_weight = -1;
		x = 0;
		y = 0;
	}
	
	/**
	 * @param x row number of the specified game block.
	 * @param y col number of the specified game block.
	 */
	public WeightNode(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	// methods
	/**
	 * compares losing weight first. if losing weight is the same, compare winning weight.
	 */
	@Override
	public int compareTo(WeightNode wn) {
		if (this.opposite_weight - wn.opposite_weight != 0) {
			return this.opposite_weight - wn.opposite_weight;
		} else {
			return this.weight - wn.weight;
		}
	}

}
