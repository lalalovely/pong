package mini;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

public class Ball {
	int x = 500/2; //x position of the ball
	int y = 0; //y position of the ball
	int dx = 1; //speed in the x direction
	int dy = 1; //speed in the y direction
	private Pong pong;
	private static final int DIAMETER = 30; //diameter of the ball

	
	public Ball(Pong pong) {
		this.pong = pong;
	}
	
	//ball's movement for the one-player pong
	public void run() throws InterruptedException {
		boolean changeDirection = true;
		if (x < 0)//if less than zero, the direction is changed to right
			dx = pong.speed;
		else if (x > pong.getWidth() - DIAMETER - 7)//if greater, the direction is changed to left
			pong.gameOver();
		else if (y < 0)
			dy = pong.speed;
		else if (y > pong.getHeight() - DIAMETER - 29)
			dy = -pong.speed;
		else if (collision()){
			pong.speed++;
			dx = -pong.speed;
			x = pong.p.getTopX() - DIAMETER;
		} else 
			changeDirection = false;
		
		if (changeDirection) //if the ball has changed its direction, there will be a sound
			Sound.BALL.play();
		x = x + dx;
		y = y + dy;
	}

	//ball's movement for the two-player pong
	public void run2() throws InterruptedException {
        if (collision1()) {
            x = pong.getWidth() / 2;
            dx = pong.speed1;
            pong.speed1++;
            Sound.BALL.play();
        }
        else if (collision2()) {
            x = pong.getWidth() / 2;
            dx = -pong.speed2;
            pong.speed2++;
            Sound.BALL.play();
        }
        else if (x < 0) {
        	dx = pong.speed1;
        	pong.speed2++;
        }
        else if (x > pong.getWidth() - DIAMETER - 7) {
        	dx = -pong.speed1;
        	pong.speed1++;
        }
        else if (y < 0){
            dy = pong.speed1;
        }else if (y > pong.getHeight() - DIAMETER - 29)
        	dy = -pong.speed1;
        if (pong.getScore1() == 3){
            JOptionPane.showMessageDialog(null, "Player 1 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        	pong.restart();
		}else if (pong.getScore2() == 3){
            JOptionPane.showMessageDialog(null, "Player 2 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        	pong.restart();
		}
		x += dx;
        y += dy;
	}
	
	//checks collision between the racket and the ball for one-player pong
	private boolean collision() {
		return pong.p.getBounds().intersects(getBounds());
	}
	
	/*
	 * the ff two functions check collision between the racket and the ball;
	 * for two-player pong;
	 */
	private boolean collision1() {
		return pong.getPlayer(1).getBounds().intersects(getBounds());
	}
	
	private boolean collision2() {
		return pong.getPlayer(2).getBounds().intersects(getBounds());
	}

	//draws the ball to the window
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
	}
	
	/*get the bounds of the rectangle that invisibly encapsulates the round(oval-shaped actually) ball;
	 * it is used in checking collision
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
