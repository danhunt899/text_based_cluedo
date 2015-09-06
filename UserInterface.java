import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

 
/**
 * The UserInterface class deals with rolling of dice and any interaction with the human playing
 * the game, I/O.
 * @author raymondgeuze
 *
 */
public class UserInterface {
	
	String[] inQuestion = new String[3];
	
	/**
	 * Simulates two die being rolled. resulting in a number between 2 and 12.
	 * @return int between 2 and 12.
	 */
	public int rollDice(){
		int min = 1;
		int max = 6;
		int first = min + (int)(Math.random() * ((max - min) + 1));
		int second = min +(int)(Math.random() * ((max - min) + 1));
		System.out.println("First dice: "+first);
		System.out.println("Second dice: "+second);
		return first + second;
	}
	
	public void printCharacters(){
		System.out.println("A) Professor Plum");
		System.out.println("B) Colonel Mustard");
		System.out.println("C) Mrs White");
		System.out.println("D) Miss Scarlet");
		System.out.println("E) Reverend Green");
		System.out.println("F) Mrs Peacock");
	}
	
	public void printWeapons(){
		System.out.println("A) Candlestick");
		System.out.println("B) Dagger");
		System.out.println("C) Lead Pipe");
		System.out.println("D) Revolver");
		System.out.println("E) Rope");
		System.out.println("F) Spanner");
	}
	
	public void printRooms(){
		System.out.println("A) Kitchen");
		System.out.println("B) Ballroom");
		System.out.println("C) Conservatory");
		System.out.println("D) Dining Room");
		System.out.println("E) Lounge");
		System.out.println("F) Hall Room");
		System.out.println("G) Study");
		System.out.println("H) Library");
		System.out.println("I) Billard Room");
	}
	
	public String[] getInQuestion(){
		return inQuestion;
	}
	
	public String askWhichPlayer(int numPlayers){
		Scanner scan = new Scanner( System.in );
		System.out.println("Which player would you like to be player " + numPlayers +"?");
		printCharacters();
		String answer;
		if(scan.hasNextInt()){ //print message if number is entered rather than letter
			System.out.println("Enter letters, not numbers");
		}
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				return "a";
			}
			else if(answer.equalsIgnoreCase("b")){
				return "b";
			}
			else if(answer.equalsIgnoreCase("c")){
				return "c";
			}
			else if(answer.equalsIgnoreCase("d")){
				return "d";
			}
			else if(answer.equalsIgnoreCase("e")){
				return "e";
			}
			else if(answer.equalsIgnoreCase("f")){
				return "f";
			}
			else{
				System.out.println("Incorrect input. (A,B,C,D,E,F)");
			}
		}
		return null; //should be unreachable
	}
	
	/**
	 * Asks the user for the number of players. ensuring there are between 3 and 6 people in the game
	 * @return the number of players
	 */
	public int howManyPlayers(){
		Scanner scan = new Scanner( System.in );
		int num = 0;
		System.out.println("How many people are going to play?");
		while(scan.hasNextInt()){
		num = scan.nextInt();
		if(num>6 || num<3){ //insures right number of people are playing. between 3 and 6
			System.out.println("Sorry you need between 3 and 6 people playing at once");
		}else{
			break;
		}
		}
		return num;	
	}
	/**
	 * Get the direction the player would like to move.
	 * @param steps, the amount the player has left in their move
	 * @return the direction they would like to move
	 */
	public String getDirection(int steps){
		Scanner scan = new Scanner( System.in );
		System.out.println("You have "+steps+" steps, which direction would you like to move? (d=down,u=up,l=left,r=right)");
		String answer;
		if(scan.hasNextInt()){ //print message if number is entered rather than letter
			System.out.println("Enter letters, not numbers");
		}
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("u")){
				return "U";
			}
			else if(answer.equalsIgnoreCase("d")){
				return "D";
			}
			else if(answer.equalsIgnoreCase("l")){
				return "L";
			}
			else if(answer.equalsIgnoreCase("r")){
				return "R";
			}
			else{
				System.out.println("Incorrect input. (d=down,u=up,l=left,r=right)");
			}
		}
		return null; //should be unreachable
	}
	
	/**
	 * Ask the player how many steps they would like to move in the decided direction
	 * @param steps, steps available to move in
	 * @return number of steps to take
	 */
	public int howManySteps(int steps,String dir){
		Scanner scan = new Scanner( System.in );
		int answer;
		System.out.println("You have "+steps+" steps how many would "
				+ "you like to go in the "+dir+" direction?");
		if(!scan.hasNextInt()){
			System.out.println("Sorry enter a number");
		}
			while(scan.hasNextInt()){
				answer=scan.nextInt();
				if(answer<=steps){
					return answer;
				}else if(answer>steps){
					System.out.println("Sorry you cant move that many steps");
				}
			}
			return 0; //should be unreachable
		}
	
	/**
	 * This method asks the user if they would like to enter room. This is called when the move
	 * they have suggested taking encounters a door.
	 * @return true if they would like to enter
	 *         false otherwise
	 */
	public boolean askEnterRoom(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to enter this room?");
		System.out.println("A) Yes");
		System.out.println("B) No");
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				return true;
			}
			if(answer.equalsIgnoreCase("b")){
				return false;
			}
			else{
				System.out.println("Incorrect input, please enter A or B");
			}
		}
		return false;
	}
	
	/**
	 * asks if user would like to end turn or make an accusation
	 * @return true if they would like to make an accusation
	 */
	public boolean askEndTurnOrAccusation(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to: ");
		System.out.println("A) End turn");
		System.out.println("B) Make an accusation");
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				return false;
			}
			if(answer.equalsIgnoreCase("b")){
				return true;
			}
			else{
				System.out.println("Incorrect input, please enter A or B");
			}
		}
		return false;
	}
	
	/**
	 * This method asks the user if they would like to make a suggestion. This is called when the player
	 * moves into a room.
	 * @return true if they would like to make a suggestion
	 *         false otherwise
	 */
	public boolean askMakeSuggestion(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to make a suggestion?");
		System.out.println("A) Yes");
		System.out.println("B) No");
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				System.out.println("Who would you like to suggest?");
				printCharacters();
				addToSuggestedPlayer();
				System.out.println("Which weapon?");
				printWeapons();
				addToSuggestedWeapon();
				return true;
			}
			if(answer.equalsIgnoreCase("b")){
				return false;
			}
			else{
				System.out.println("Incorrect input. (A,B,C,D,E,F..)");
			}
		}
		return false;
	}
	
	/**
	 * Add the room which the player thinks is the murder room into the inQuestion array as the first
	 * element. inQuestion[0] will always reference the player in question
	 */
	public void addToSuggestedPlayer(){
		Scanner scan = new Scanner( System.in );
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				inQuestion[0] = "Professor Plum";
				return;
			}
			else if(answer.equalsIgnoreCase("b")){
				inQuestion[0] = "Colonel Mustard";
				return;
			}
			else if(answer.equalsIgnoreCase("c")){
				inQuestion[0] = "Mrs White";
				return;
			}
			else if(answer.equalsIgnoreCase("d")){
				inQuestion[0] = "Miss Scarlet";
				return;
			}
			else if(answer.equalsIgnoreCase("e")){
				inQuestion[0] = "Reverend Green";
				return;
			}
			else if(answer.equalsIgnoreCase("f")){
				inQuestion[0] = "Mrs Peacock";
				return;
			}
			else{
				System.out.println("Incorrect input. (A,B,C,D,E,F)");
			}
		}
	}
	
	/**
	 * Add the weapon which the player thinks is the murder weapon into the inQuestion array as the second
	 * element. inQuestion[1] will always reference the weapon in question
	 */
	public void addToSuggestedWeapon(){
		Scanner scan = new Scanner( System.in );
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				inQuestion[1] = "Candlestick";
				return;
			}
			else if(answer.equalsIgnoreCase("b")){
				inQuestion[1] = "Dagger";
				return;
			}
			else if(answer.equalsIgnoreCase("c")){
				inQuestion[1] = "Lead Pipe";
				return;
			}
			else if(answer.equalsIgnoreCase("d")){
				inQuestion[1] = "Revolver";
				return;
			}
			else if(answer.equalsIgnoreCase("e")){
				inQuestion[1] = "Rope";
				return;
			}
			else if(answer.equalsIgnoreCase("f")){
				inQuestion[1] = "Spanner";
				return;
			}
			else{
				System.out.println("Incorrect input. (A,B,C,D,E,F)");
			}
		}
	}
	
	/**
	 * Add the room which the player thinks is the murder room into the inQuestion array as the third
	 * element. inQuestion[2] will always reference the room in question
	 */
	public void addToSuggestedRoom(){
		Scanner scan = new Scanner( System.in );
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				inQuestion[2] = "Kitchen";
				return;
			}
			else if(answer.equalsIgnoreCase("b")){
				inQuestion[2] = "Ballroom";
				return;
			}
			else if(answer.equalsIgnoreCase("c")){
				inQuestion[2] = "Conservatory";
				return;
			}
			else if(answer.equalsIgnoreCase("d")){
				inQuestion[2] = "Dining Room";
				return;
			}
			else if(answer.equalsIgnoreCase("e")){
				inQuestion[2] = "Lounge";
				return;
			}
			else if(answer.equalsIgnoreCase("f")){
				inQuestion[2] = "Hall Room";
				return;
			}
			else if(answer.equalsIgnoreCase("g")){
				inQuestion[2] = "Study";
				return;
			}
			else if(answer.equalsIgnoreCase("h")){
				inQuestion[2] = "Library";
				return;
			}
			else if(answer.equalsIgnoreCase("i")){
				inQuestion[2] = "Billard Room";
				return;
			}
			else{
				System.out.println("Incorrect input. (A,B,C,D,E,F..)");
			}
		}
	}
	
	/**
	 * This method asks the user if they would like to make an accusation. This is called at the end or
	 * start of a players turn.
	 * @return true if they would like to make a suggestion
	 *         false otherwise
	 */
	public boolean askMakeAccusation(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to make an accusation?");
		System.out.println("A) Yes");
		System.out.println("B) No");
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				System.out.println("Who would you like to accuse?");
				printCharacters();
				addToSuggestedPlayer();
				System.out.println("Which weapon?");
				printWeapons();
				addToSuggestedWeapon();
				System.out.println("Which room?");
				printRooms();
				addToSuggestedRoom();
				return true;
			}
			if(answer.equalsIgnoreCase("b")){
				return false;
			}
			else{
				System.out.println("Incorrect input, please enter A or B");
			}
		}
		return false;
	}
	
	/**
	 * Asks the player if they would like to play again
	 */
	public boolean askPlayAgain(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to play again?");
		System.out.println("A) Yes");
		System.out.println("B) No");
		String answer;
		while(scan.hasNext()){
			answer = scan.next();
			if(answer.equalsIgnoreCase("a")){
				return true;
			}
			if(answer.equalsIgnoreCase("b")){
				return false;
			}
			else{
				System.out.println("Incorrect input, please enter A or B");
			}
		}
		return false;
	}
	
	/**
	 * This method needs to be in the Player class.
	 * Prints the names of all the cards help by this Player
	 * @param cards , list of cards help by this player
	 */
	public void printPlayersCards(List<Card> cards){
		for(Card c:cards){
			System.out.println("You hold"+c.getName());
		}
	}
	
	/**
	 * Asks the user if they would like to roll the dice or make an accusation
	 * @return 1, to represent wanting to roll the dice
	 * 		   2, to represent wanting to make an accusation
	 *  	   0, if an error occurs
	 */		   
	public int rollDiceOrAccusation(){
		Scanner scan = new Scanner( System.in );
		System.out.println("Would you like to:");
		System.out.println("A) Roll the dice");
		System.out.println("B) Make an accusation");
		String answer;
		while(scan.hasNext()){ //need to deal with errors here rather than in Game.run()
			answer = scan.next();
			if(answer.equalsIgnoreCase("A")){
				return 1;
			}
			else if(answer.equalsIgnoreCase("B")){
				return 2;
			}
			else{
				System.out.println("Sorry, please enter A or B");
			}
		}
		return 0;

	}
}
