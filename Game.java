package mini;

import javax.swing.JOptionPane;
import mini.Pong;

//main class
public class Game {
	/*
	 * displays a pop-up window that would urges user to choose the type of game
	 * a one-player or two-player
	 */
	public static void main(String[] args) {
		String[] choices = {"One Player", "Two Players"};
	    String input = (String) JOptionPane.showInputDialog(null, 
	    				"Hello!" + 
	    				"\nHow do you like to play?",
	    				"P O N G    O P T I O N", 
	    				JOptionPane.QUESTION_MESSAGE, 
	    				null, 
	    				choices, 
	    				choices[0]);
	    
	    if (input == "One Player") {
	    	/*if the player chooses one-player pong, a Pong object is created with 1 as argument
	    	 * indicating the number of player;
	    	 */
	    	Pong pong1 = new Pong(1); 
			Pong.startGame(pong1);
	    } else if (input == "Two Players"){
	    	/*if the player chooses two-player pong, a Pong object is created with 2 as argument
	    	 * indicating the number of players
	    	 */
	    	Pong pong2 = new Pong(2);
			Pong.startGame(pong2);
	    } else {
	    	//exists
	    	System.exit(0);
	    }
		
	}
}
