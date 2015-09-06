/**
 * This class represents the weapons of the game.
 * @author danhunt
 * @author rayg
 *
 */
public class Weapon {
	
	private String name; // stores the name of weapon, candlestick, dagger, leadpipe, revolver, rope, spanner
	private String location; // keeps track of the square of board which the player is sitting on/replaced
	private int xLoc; //current x location
	private int yLoc; //current y location
	private String initials; // the character which represents the weapon on the board.
	
	/**
	 * Constructor: Creates a new object and assigns values to the fields based on the name parameter
	 * @param name
	 */
	public Weapon(String name){
		this.name = name;
		if(name.equals("Candlestick")){
			this.xLoc = 24;
			this.yLoc = 15;
			this.location = "Y";
			this.initials = "!";
		}
		else if(name.equals("Dagger")){
			this.xLoc = 0; 
			this.yLoc = 12;
			this.location = "G";
			this.initials = "%";
		}
		else if(name.equals("Lead Pipe")){
			this.xLoc = 20;
			this.yLoc = 1; 
			this.location = "C";
			this.initials = "$";
		}
		else if(name.equals("Revolver")){
			this.xLoc = 2; 
			this.yLoc = 24;
			this.location = "L";
			this.initials = "*";
		}
		else if(name.equals("Rope")){
			this.xLoc = 11; 
			this.yLoc = 24; 
			this.location = "H";
			this.initials = "&";
		}
		else if(name.equals("Spanner")){
			this.xLoc = 0;
			this.yLoc = 2;
			this.location = "K";
			this.initials = "@";
		}
	}
	
	 
	 /**
	  * 
	  * @return the board character that the character is occupying
	  */
	public String getBoardLoc(){
		return this.location;
	}
	
	public int getXLocation(){
		return this.xLoc;
	}
	
	public int getYLocation(){
		return this.yLoc;
	}
	
	public String getInitials(){
		return this.initials;
	}
		
	public String getName(){
		return this.name;
	}
	/**
	 * 
	 * @return returns the location of the weapon as a full name/word.
	 */
	public String getLocation(){
		String code =  this.location;
		
		switch(code){
		case "K": return "Kitchen";
		case "B": return "Ballroom";
		case "C": return "Conservatory";
		case "G": return "Dining Room";
		case "L": return "Lounge";
		case "W": return "Wall";
		case "H": return "Hall Room";
		case "S": return "Study";
		case "Y": return "Library";
		case "I": return "Billard Room";
		case "O": return "Cluedo";
		case "D": return "Door";
		case ".": return "Hallway";
		default : return code;
		}
	}
	/**
	 * Moves the weapon from its current location to a new location specified by the room parameter
	 * @param room the room the weapon is being moved to.
	 */
	public void moveTo(String room){
		switch (room) {
		case "Kitchen":
			this.location = "K";
			this.xLoc = 4;
			this.yLoc = 1;
			break;
		case "Ballroom":
			this.location = "B";
			this.xLoc = 13;
			this.yLoc = 1;
			break;
		case "Conservatory":
			this.location = "C";
			this.xLoc = 24;
			this.yLoc = 2;
			break;
		case "Dining Room":
			this.location = "G";
			this.xLoc = 0;
			this.yLoc = 10;
			break;
		case "Lounge":
			this.location = "L";
			this.xLoc = 0;
			this.yLoc = 19;
			break;
		case "Hall Room":
			this.location = "H";
			this.xLoc = 13;
			this.yLoc = 24;
			break;
		case "Study":
			this.location = "S";
			this.xLoc = 21;
			this.yLoc = 24;
			break;
		case "Library":
			this.location = "Y";
			this.xLoc = 24;
			this.yLoc = 16;
			break;
		case "Billard Room":
			this.location = "I";
			this.xLoc = 24;
			this.yLoc = 8;
			break;
		default:
		}
	}
	
	public void setLocation(String location){
		this.location = location;
	}
}


