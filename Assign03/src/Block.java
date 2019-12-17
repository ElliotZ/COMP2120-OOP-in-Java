/**
 * Class Block. Basic container for a block on the Tic-Tac-Toe game board.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public class Block implements Global {
	// attributes
	private int state;  // -1 is X, 0 is empty, 1 is O.
	
	// constructor
	/**
	 * default constructor. initializes the block to be empty.
	 */
	public Block() {
		this.setState(EMPTY);
	}
	
	// methods
	/**
	 * @param state the state to be set. Can either be X, O or EMPTY (use global constants).
	 * @return current value of this.state
	 */
	public int setState(int state) {
		this.state = state;
		return this.state;
	}
	
	/**
	 * @return current value of this.state
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * @return a string representation of the state of this block. Can either be X, O or EMPTY (a space).
	 */
	@Override
	public String toString() {
		if (this.state == EMPTY) {
			return " ";
		} else if (this.state == X) {
			return "X";
		} else {  // this.state == O
			return "O";
		}
	}

}
