import java.util.*;

/**
 * Class Game. Maintainer and host of the Tic-Tac-Toe game.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public class Game implements Global {
	// constants
	public static int MAIN_MENU = 1;
	public static int PLAYER_CREATION = 2;
	public static int GAME = 3;
	
	// attributes
	private Board game_board;
	private Player p1;  // dynamically created at runtime, not constructed
	private Player p2;
	private boolean turn_of_p1;  // for coin toss
	
	// constructor
	/**
	 * default constructor. initializes the game board, but the two players must be initialized
	 * elsewhere (i. e. at runtime).
	 */
	public Game() {
		game_board = new Board();
		p1 = null;
		p2 = null;
		turn_of_p1 = false;
	}
	
	// methods
	/**
	 * Starts the game.
	 */
	public void Start() {
		@SuppressWarnings("resource")  // no need to close System.in
		Scanner sc = new Scanner(System.in);
		
		// welcoming screen
		Menu(MAIN_MENU);
		System.out.println("Press any key to start the game!");
		sc.nextLine();
		
		// initialize players
		Menu(PLAYER_CREATION);
		createPlayer();
		System.out.println("Players are created.");
		
		coinToss();
		Menu(GAME);
		
		// game starts
		printBoard();
		while(game_board.getState() == EMPTY) {
			gameRound();
		}
		
		// game ends
		if (game_board.getState() == DRAW) {
			System.out.println("It\'s a Draw!");
		} else if (game_board.getState() == OWIN) {
			System.out.println("O Wins!");
		} else {  // game_board.getState() == XWIN
			System.out.println("X Wins!");
		}
		
	}
	
	// internal methods
	/**
	 * prints different banners depending on input.
	 * @param type can either be MAIN_MENU, PLAYER_CREATION, or GAME (use constants).
	 */
	private void Menu(int type) {
		if (type == MAIN_MENU) {
			System.out.println("=================================================");
			System.out.println("|                                               |");
			System.out.println("|                 Tic-Tac-Toe                   |");
			System.out.println("|                                               |");
			System.out.println("=================================================");
		} else if (type == PLAYER_CREATION) {
			System.out.println("=================================================");
			System.out.println("|                                               |");
			System.out.println("|               Player Creation                 |");
			System.out.println("|                                               |");
			System.out.println("=================================================");
		} else if (type == GAME) {
			System.out.println("=================================================");
			System.out.println("|                                               |");
			System.out.println("|                  Game Start!                  |");
			System.out.println("|                                               |");
			System.out.println("=================================================");
		}
	}
	
	/**
	 * Player Creation. the two players maintained in this class is initialized here.<br><br>
	 * if at any point an input is illegal, this makes the user start over at a
	 * reasonable point.
	 */
	private void createPlayer() {
		@SuppressWarnings("resource")  // no need to close System.in
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean creationSuccessful = false;  // for input error handling
		
		// player 1
		do {
			System.out.println("Creating player 1. Is this a Human player? y/n");
			input = sc.nextLine();
			if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
				System.out.println("Please enter the name of this player.");
				input = sc.nextLine();
				if (input.isEmpty()) {
					System.out.println("Player name must not be empty!");
					continue;
				}
				p1 = new HumanPlayer(EMPTY, input, this.game_board);  // player symbol is managed in coinToss()
			} else {
				System.out.println("Please select the difficulty of this CPU player.");
				System.out.println("1 - Easy; 2 - Normal; 3 - UNBEATABLE");
				int diff;
				try {
					diff = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Illegal input.");
					sc.nextLine();
					continue;
				}
				if (diff > 3 || diff < 1) {
					System.out.println("Illegal input.");
					continue;
				}
				sc.nextLine();
				p1 = new AIPlayer(EMPTY, "CPU", this.game_board, diff);  // player symbol is managed in coinToss()
			}
			creationSuccessful = true;
		} while (!creationSuccessful);
		
		// player 2
		creationSuccessful = false;
		do {
			System.out.println("Creating player 2. Is this a Human player? y/n");
			input = sc.nextLine();
			if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
				System.out.println("Please enter the name of this player.");
				input = sc.nextLine();
				if (input.isEmpty()) {
					System.out.println("Player name must not be empty!");
					continue;
				}
				p2 = new HumanPlayer(EMPTY, input, this.game_board);  // player symbol is managed in coinToss()
			} else {
				System.out.println("Please select the difficulty of this CPU player.");
				System.out.println("1 - Easy; 2 - Normal; 3 - UNBEATABLE");
				int diff;
				try {
					diff = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Illegal input.");
					continue;
				}
				if (diff > 3 || diff < 1) {
					System.out.println("Illegal input.");
					continue;
				}
				p2 = new AIPlayer(EMPTY, "CPU", this.game_board, diff);  // player symbol is managed in coinToss()
			}
			creationSuccessful = true;
		} while (!creationSuccessful);
		
	}
	
	/**
	 * a coin toss (in fact, 2) to determine the pieces each player holds, and which player gets the
	 * first hand.
	 */
	private void coinToss() {
		System.out.println("Tossing Coins to decide player symbols and first one to go...");
		Random rand = new Random();
		
		// toss for game pieces
		boolean p1PlaysX = rand.nextBoolean();
		p1.setSymbol(p1PlaysX ? X : O);
		p2.setSymbol(p1PlaysX ? O : X);
		
		// toss for first hand
		this.turn_of_p1 = rand.nextBoolean();
		System.out.println("Player 1 plays " + (p1PlaysX ? "X" : "O") +
				", and Player 2 plays " + (p1PlaysX ? "O" : "X") + ".");
		if (this.turn_of_p1) {
			System.out.println("Player 1 goes first. Have fun!");
		} else {
			System.out.println("Player 2 goes first. Have fun!");
		}
	}
	
	/**
	 * prints the board on screen.
	 */
	private void printBoard() {
		System.out.print("   ");
		for (int i = 0; i < BOARD_SIZE; ++i) {
			System.out.print(" " + (i + 1) + "  ");
		}
		System.out.print(" \n  -");
		for (int i = 0; i < BOARD_SIZE; ++i) {
			System.out.print("----");
		}
		System.out.println();
		
		for (int i = 0; i < BOARD_SIZE; ++i) {
			System.out.print((char) (i + 'A'));
			System.out.print(" |");
			for (int j = 0; j < BOARD_SIZE; ++j) {
				System.out.print(" " + (this.game_board.getGrid()[i][j]) + " |");
			}
			System.out.println();
			System.out.print("  -");
			for (int j = 0; j < BOARD_SIZE; ++j) {
				System.out.print("----");
			}
			System.out.println();
		}
	}
	
	/**
	 * decides which player gets the next hand, and lets him make a move.
	 */
	private void gameRound() {
		if (turn_of_p1) {
			p1.play();
			printBoard();
		} else {
			p2.play();
			printBoard();
		}
		turn_of_p1 = turn_of_p1 ^ true;  // turn_of_p1 XOR true reverts it
	}

}
