package mini;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pong extends JPanel{
	/*
	 * constructs player (type Racket) and ball objects;
	 * p is for one-player pong and p1 & p2 are for two-player pong;
	 */
	Racket p = new Racket(this, KeyEvent.VK_DOWN, KeyEvent.VK_UP, 420, 10);
	Racket p1 = new Racket(this, KeyEvent.VK_Z, KeyEvent.VK_A, 20, 70);
	Racket p2 = new Racket(this, KeyEvent.VK_M, KeyEvent.VK_K, 460, 0);
	Ball b = new Ball(this);
	/*
	 * dimension of window
	 */
	private static int width = 500;
	private static int height = 300;
	private int numPlayers;  //number of player(s)
	int speed = 1, speed1 = 1, speed2 = 1;
	
	//returns the player using their number
	public Racket getPlayer(int playerNo) {
        if (playerNo == 1)
            return p1;
        else
            return p2;
    }
	
	//returns score for one-player pong
	public int getScore() {
		return speed - 1;
	}
	
	//returns score for player 1 of two-player pong
	public int getScore1() {
		return speed1 - 1;
	}
	
	//returns score for player 2 of two-player pong
	public int getScore2() {
		return speed2 - 1;
	}
	
	/*
	 * constructor;
	 * gets keyboard input
	 */
	public Pong(int numPlayers) {
		this.numPlayers = numPlayers;
		if (numPlayers == 1) {
			addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
					p.keyReleased(e);
				}

				public void keyPressed(KeyEvent e) {
					p.keyPressed(e);
				}
			});
			setFocusable(true);
			Sound.BACK.play();
		} else if (numPlayers == 2) {
			addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
					p1.keyReleased(e);
				}

				public void keyPressed(KeyEvent e) {
					p1.keyPressed(e);
				}
			});
			setFocusable(true);
			addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent f) {
				}

				public void keyReleased(KeyEvent f) {
					p2.keyReleased(f);
				}

				public void keyPressed(KeyEvent f) {
					p2.keyPressed(f);
				}
			});
			setFocusable(true);
			Sound.BACK.play();
		}
	}
	
	//getters and setters
	public int getWidth() {	return width;}
	public int getHeight() {return height;}
	public int getNumPlayers() {return numPlayers;}
	public void setNumPlayers(int num) {numPlayers = num;}
	
	//run everything
	public void run() {
		/*
		 * calls the function that enables the ball to move and the rackets to move as well;
		 * it checks whether it is a one-player pong or a two-player pong;
		 */
		if (numPlayers == 1) {
			try {
				b.run();
				p.move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (numPlayers == 2) {
			try {
				b.run2();
				p1.move();
				p2.move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//draw everything on the window
	public void paint(Graphics g) {
		/*
		 * draws the ball and the racket(s) in the window;
		 * the check is to determine whether it would be a one-player or two-player pong;
		 */
		if (numPlayers == 1) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			//g2d.setColor(Color.RED);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			b.paint(g2d);
			p.paint1(g2d);
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(getScore()), width/2, 30);
		} else if (numPlayers == 2) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			//g2d.setColor(Color.RED);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			b.paint(g2d);
			p1.paint1(g2d);
			p2.paint2(g2d);
			g.setColor(Color.RED);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			g.drawString("Player 1: " + String.valueOf(getScore1()), 10, 30);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			g.drawString("Player 2: " + String.valueOf(getScore2()), width-100, 30);
		}
		
	}
	
	//run game
	public static void startGame(Pong pong) {
		/*
		 * the ff. block of code creates and displays a window;
		 */
		JFrame frame = new JFrame();
		frame.setTitle("PONG - Mini Tennis");
		frame.add(pong);
		frame.pack();
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * game loop; while true, run() is called to execute the game(the movements
		 * of the ball and the racket(s);
		 */
		while (true) {
			try {
				pong.run();
				pong.repaint();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * restart; exception is thrown but try and catch would also do the work;
	 * the exception is on the startGame(Pong p) since it uses Thread. sleep()
	 * and there is an exception that needs attention;
	 */
	public void restart() throws InterruptedException {
		/*
		 * it displays a pop-up window that would confirm if we want to play again or not;
		 */
		int dialogButton = JOptionPane.YES_NO_OPTION;
		dialogButton = JOptionPane.showConfirmDialog(this, "Play again?","RESTART", dialogButton);
		if(dialogButton == JOptionPane.YES_OPTION) {
			Pong pong = new Pong(numPlayers);
			startGame(pong);
		} else if(dialogButton == JOptionPane.NO_OPTION) {
			System.exit(ABORT);
		}
	}
	
	/*
	 * this pops a dialog when the game is over;
	 * the game would be over for a one-player pong when the ball goes beyond the racket's position;
	 * there is no game over for two-player pong since the player to reach 3 pts first is the winner
	 * and the game would stop there;
	 */
	public void gameOver() throws InterruptedException {
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "Game over", 
				"Game Over", JOptionPane.YES_NO_OPTION);
		restart();//calls restart so that the player could play again;
	}
	
}
