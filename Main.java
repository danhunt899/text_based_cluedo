
public class Main {

	public static void main(String[] args) {
		
		Game game = new Game();
		while(true){
			game.run();
			if(game.askPlayAgain()){ //not sure if this is good idea? will call UI.askPlayGame
				game = new Game();
			}else{
				System.out.println("Goodbye");
				break;
			}
		}
		
		
		
		
		
//		int steps;
//		int moveSteps;
//		String dir;
//		UserInterface u = new UserInterface();
//		System.out.println(u.howManyPlayers());
//		steps = u.rollDice();
//		System.out.println(u.rollDiceOrAccusation());
//		while(steps>0){
//		dir = u.getDirection(steps);
//		moveSteps = u.howManySteps(steps,dir);
//		steps -= moveSteps;
//		
//
//		}
//		System.out.println("Your move is over dumbo");
	}

	/**
	 * game loop? while(players>0){ 
	 * 					while(steps>0){
	 * if player makes an accusation in this time and is wrong then remove them from players
	 * 
	 */
}
