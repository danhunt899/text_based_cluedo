import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*
 * This method is the cluedo game, it controls and creates the board, players, weapons etc 
 */
public class Game {
	private Board board;
	private Player[] players;
	private Weapon[] weapons;
	private List<Player> controlledPlayers = new ArrayList<Player>();// human players
	private Set<Card> cards = new HashSet<Card>();
	private UserInterface UI = new UserInterface();
	private String[] solution = new String[3];
	private String dir; //direction to be moved in
	private int steps = 0; //steps player has left
	private int moveSteps=0; //how many steps player would like to move
	String playerSuggest; //variables in suggestion
	String weaponSuggest;
	String roomSuggest;
	int nplayers; //number of controlled players (humans playing)
	Weapon weapon;
	

	public Game(){
		
		//create the players, then pass the players to the board
		this.players = new Player[6];
		players[0] = new Player("Professor Plum");
		players[1] = new Player("Colonel Mustard");
		players[2] = new Player("Mrs White");
		players[3] = new Player("Miss Scarlet");
		players[4] = new Player("Reverend Green");
		players[5] = new Player("Mrs Peacock");
		
		//create the weapons
		this.weapons = new Weapon[6];
		weapons[0] = new Weapon("Candlestick");
		weapons[1] = new Weapon("Dagger");
		weapons[2] = new Weapon("Lead Pipe");
		weapons[3] = new Weapon("Revolver");
		weapons[4] = new Weapon("Rope");
		weapons[5] = new Weapon("Spanner");
		
		/*creates all player cards*/
		cards.add(new Card("Player","Professor Plum"));
		cards.add(new Card("Player","Colonel Mustard"));
		cards.add(new Card("Player","Mrs White"));
		cards.add(new Card("Player","Miss Scarlet"));
		cards.add(new Card("Player","Reverend Green"));
		cards.add(new Card("Player","Mrs Peacock"));
		/*creates all weapon cards*/
		cards.add(new Card("Weapon","Candlestick"));
		cards.add(new Card("Weapon","Dagger"));
		cards.add(new Card("Weapon","Lead Pipe"));
		cards.add(new Card("Weapon","Revolver"));
		cards.add(new Card("Weapon","Rope"));
		cards.add(new Card("Weapon","Spanner"));
		/*creates all room cards*/
		cards.add(new Card("Room","Kitchen"));
		cards.add(new Card("Room","Ballroom"));
		cards.add(new Card("Room","Conservatory"));
		cards.add(new Card("Room","Dining Room"));
		cards.add(new Card("Room","Lounge"));
		cards.add(new Card("Room","Hall Room"));
		cards.add(new Card("Room","Study"));
		cards.add(new Card("Room","Library"));
		cards.add(new Card("Room","Billard Room"));
		
		this.board = new Board(players,weapons);
	}
	
	/**
	 * Builds an arraylist of they players who are controlled (human)
	 * this can then be used to determine who is left in the game or who has lost
	 * @param letter, hardcoded letters representing characters. A = profesor plum and so forth
	 */
	public boolean buildControlled(String letter){
		if(letter.equals("a")){
			if(!controlledPlayers.contains(players[0])){
			controlledPlayers.add(players[0]);
 			return true;
			}
		}
		else if(letter.equals("b")){
			if(!controlledPlayers.contains(players[1])){
			controlledPlayers.add(players[1]);
			return true;
			}
		}
		else if(letter.equals("c")){
			if(!controlledPlayers.contains(players[2])){
			controlledPlayers.add(players[2]);
			return true;
			}
		}
		else if(letter.equals("d")){
			if(!controlledPlayers.contains(players[3])){
			controlledPlayers.add(players[3]);
			return true;
			}
		}
		else if(letter.equals("e")){
			if(!controlledPlayers.contains(players[4])){
			controlledPlayers.add(players[4]);
			return true;
			}
		}
		else if(letter.equals("f")){
			if(!controlledPlayers.contains(players[5])){
			controlledPlayers.add(players[5]);
			return true;
			}
		}
		for(Player p:controlledPlayers){
			p.setControlled();
		}
		System.out.println("Sorry that player is taken, choose again");
		return false;
	}
	
	/**
	 * Deals all the cards to the controlled players (humans), giving each an amount relative to 
	 * how many people are playing
	 * @param numPlayers, number of controlled players
	 */
	public void dealCards(int numPlayers){
		int i=0;
		for(Card c:cards){
			controlledPlayers.get(i).addToCards(c); // give this card to this player
			i++; //increment to next player while incrementing to next card
			if(i==numPlayers){ //start from first player again
				i=0;
			}
		}
		
//		for(Player p:controlledPlayers){ //was for testing, leaving just incase need to test again
//			System.out.println(p.getName());
//			 p.printCards();
//		}
	}
	/**
	 *
	 * @return The board for testing purposes
	 */
	public Board getBoard(){
		return this.board;
	}
	/**
	 * 
	 * @return the player array, for testing
	 */
	public Player[] getPlayers(){
		return this.players;
	}
	/**
	 * 
	 * @return the weapon array, for testing.
	 */
	public Weapon[] getWeapons(){
		return this.weapons;
	}
	
	/**
	 * Fills the solution envelope, removing the cards it adds from the set to ensure they cannot be given to
	 * players. three loops not ideal but getting job done, can change if need be
	 */
	public void makeSolution(){
		for(Card c:cards){
			if(c.getType().equalsIgnoreCase("player")){
				solution[0] = c.getName();
				cards.remove(c);
				break;
			}
		}
		for(Card c:cards){
			if(c.getType().equalsIgnoreCase("weapon")){
				solution[1] = c.getName();
				cards.remove(c);
				break;
			}
		}
		for(Card c:cards){
			if(c.getType().equalsIgnoreCase("room")){
				solution[2] = c.getName();
				cards.remove(c);
				break;
			}
		}
		
	}
	
	/**
	 * Checks a players accusation against the the cards held in the solution envelope
	 * @param player, accused player
	 * @param weapon, accused weapon
	 * @param room, accused room
	 * @return true if matches solution, player wins
	 *  	   false if doesnt match, player loses
	 */
	public boolean checkAccusation(String player,String weapon,String room){
		if(solution[0].equalsIgnoreCase(player)){
			if(solution[1].equalsIgnoreCase(weapon)){
				if(solution[2].equalsIgnoreCase(room)){
					return true; //was correct, player wins
				}
			}
		}
		return false; //accusation was incorrect, player loses
	}
	
	/**
	 * Takes the suggested player, weapon and room and checks every players hand of cards to see if they can
	 * appeal the suggestion. 
	 * @param player, suggested player
	 * @param weapon. suggested weapon
	 * @param room, suggested room
	 * @return returns false if someone contains the suggested card
	 * 		   otherwise true if the suggestion cannot be denied
	 */
	public boolean checkSuggestion(String currentName,String player,String weapon,String room){
		for(Player p:controlledPlayers){
			//if(p.getName()!=currentName){
				for(Card c: p.getCards()){ //checks each card in each players hand 
					if(c.getName().equalsIgnoreCase(player) || c.getName().equalsIgnoreCase(weapon) ||
							c.getName().equalsIgnoreCase(room)){
						return false;
					}
				}		
			//}
		}
		return true;
	}
	/**
	 * 
	 * @return The solution to the game 
	 */
	public String[] getSolution(){
		return this.solution;
	}
	/**
	 * This method practically is the game, controls responses to the user commands. 
	 * and creates the game flow from player to player
	 */
	public void run(){
		int diceOrAcc = 0; // indicator int for rolling dice or making accusation
		int numPlayers;
		String roomNear; // the room this player is near and able to enter

		
		numPlayers=UI.howManyPlayers();
		nplayers = numPlayers;
		while(numPlayers>0){
			if(buildControlled(UI.askWhichPlayer(numPlayers))){ //builds arraylist of players who are controlled (human)
			numPlayers--;
			}
		}
		makeSolution();
		dealCards(nplayers); //deals all the cards to the controlled players (humans)
		while(nplayers>1){ //when size = 1 we have a winner
			for(Player p:controlledPlayers){ // each players turn pretty much
				System.out.println("It is now "+p.getName()+"'s turn");
				diceOrAcc = UI.rollDiceOrAccusation(); 
				if(diceOrAcc == 1){
					steps = UI.rollDice();
				}else if(diceOrAcc == 2){
					if(accusation()){
						return;
					}
				}
				steploop:
				while(steps>0){ //if steps = 0 it is the next players turn
					board.draw();
					if(board.inRoom(p)){
						System.out.println(p.getBoardLoc());
						board.exitRoom(p);
						//board.draw();
						//work out what exit, call board.exitRoom() or someting liek this
					}
					//need to work out if inRoom(), if true then ask what exit to leave
					//if false then continue as usual like below
					move(p);
					board.draw();
					roomNear = board.roomAdjacentTo(p);
					if(roomNear != null){
						if(UI.askEnterRoom()){//ask if player would like to enter the room they are next to a door
							steps = 0;
							board.movePlayerToRoom(p, roomNear);
							board.draw();
							if(UI.askMakeSuggestion()){
								playerSuggest = UI.getInQuestion()[0]; //player in question
								weaponSuggest = UI.getInQuestion()[1]; //weapon in question
								roomSuggest = roomNear;//UI.getInQuestion()[2];  //room in question
								moveWeapon(weaponSuggest, roomNear); // move weapon to room
								movePlayer(playerSuggest, roomNear);// move player as well.
								p.makeSuggestion(playerSuggest, weaponSuggest, roomSuggest);
								System.out.println("You have suggested "+playerSuggest+ " in the "+ roomSuggest+ " with the "+weaponSuggest+".");
								if(checkSuggestion(p.getName(),playerSuggest, weaponSuggest,roomSuggest)){
									if(accusation()){
										return;
									}
									else{
										break steploop;
									}
								}
								else{
									System.out.println("Your suggestion was incorrect");
									//this should really print the first thing that denied the suggestion
								}
							}
						}
					 }
				//if still have steps will start loop again..
				}
//				if(UI.askEndTurnOrAccusation()){ tbh dont think we need this, moving your steps shouldnt change
				//mind whther you wanna make an accy or not.. ya know
//					accusation();
//				}
			}
		}
		System.out.println("The remaining player wins!!"); //all players have been removed so this player wins!
	}
	
	private void movePlayer(String playerSuggest, String room) {
		switch(playerSuggest){
		case("Professor Plum"):
			board.movePlayerToRoom(players[0], room);
			break;
		case("Colonel Mustard"):
			board.movePlayerToRoom(players[1], room);
			break;
		case("Mrs White"):
			board.movePlayerToRoom(players[2], room);
			break;
		case("Miss Scarlet"):
			board.movePlayerToRoom(players[3], room);
			break;
		case("Reverend Green"):
			board.movePlayerToRoom(players[4], room);
			break;
		case("Mrs Peacock"):
			board.movePlayerToRoom(players[5], room);
			break;
		default: break;
		}
		
	}

	/**
	 * Asks the user if they would like to play again
	 * @return
	 */
	public boolean askPlayAgain(){
		if(UI.askPlayAgain()){
			return true;
		}
		return false;
	}
	
	/**
	 * moves the character, handing if the move cant be done by contunuing to ask till the user 
	 * gives a doable movement
	 * @param p, the player to moved
	 */
	public void move(Player p){
		dir = UI.getDirection(steps);
		moveSteps = UI.howManySteps(steps, dir);
		while(!board.movePlayer(p, moveSteps, dir)){
			System.out.println("Sorry that move cant be done");
			dir = UI.getDirection(steps);
			moveSteps = UI.howManySteps(steps, dir);
		}
		steps-= moveSteps;
	}
	
	/**
	 * Deals with making an accusation, ask what players..
	 * calls all the accusation associated methods 
	 */
	public boolean accusation(){
		if(UI.askMakeAccusation()){
		playerSuggest = UI.getInQuestion()[0];
		weaponSuggest = UI.getInQuestion()[1];
		roomSuggest = UI.getInQuestion()[2];
		if(checkAccusation(playerSuggest,weaponSuggest,roomSuggest)){
			System.out.println("You Win!!");
			return true;
		}
		else{
			System.out.println("Sorry, you lose");
			nplayers-=1;
		}
	}
		return false;
	}
	
	/**
	 * sets field weapon to be the weapon that is needing to be moved
	 */
	public void moveWeapon(String weaponSuggest, String room){
		switch(weaponSuggest){
		case("Candlestick"):
			board.moveWeaponToRoom(weapons[0], room);
			break;
		case("Dagger"):
			board.moveWeaponToRoom(weapons[1], room);
			break;
		case("Lead Pipe"):
			board.moveWeaponToRoom(weapons[2], room);
			break;
		case("Revolver"):
			board.moveWeaponToRoom(weapons[3], room);
			break;
		case("Rope"):
			board.moveWeaponToRoom(weapons[4], room);
			break;
		case("Spanner"):
			board.moveWeaponToRoom(weapons[5], room);
			break;
		default: break;
		}
	}
}