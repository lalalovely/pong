package mini;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {
	
	public static final int WIDTH = 10, HEIGHT = 60; //dimension of the racket
	public int x, y; //x and y positions
	public int dy;
	public int down, up; //keyboard inputs
	Pong pong;
	
	/*constructor; accepts Pong, keyboard inputs, position
	 * each player has different keyboard inputs so i just included it in the constructor;
	 */
	public Racket(Pong pong, int down, int up, int x, int y) {
		this.pong = pong;
		this.down = down;
		this.up = up;
		this.x = x;
		this.y = y;
		dy = 0;
	}
	
	//racket's movement; always in the y direction
	public void move() {
		if (y + dy > 0 && y + dy <= pong.getHeight() - HEIGHT)
			y = y + dy;
	}
	
	//draws the racket int the window
	public void paint1(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void paint2(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	//for keyboard inputs
	public void keyReleased(KeyEvent e) {
		dy = 0;
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == up)
			dy = -1;
		if (e.getKeyCode() == down)
			dy = 1;
	}
	
	//get the bounds of the rectangle; used for checking collision
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	//gets the x position of the racket
	public int getTopX() {
		return x;
	}
	
}
