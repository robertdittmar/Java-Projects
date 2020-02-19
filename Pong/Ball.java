//class that represents the game ball for Pong, with methods to draw, sense paddle collisions,
//and move the ball

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	//fields for positions, sizes, and speed
	private double xPos, yPos, xSpeed, ySpeed;
	private int size;
	
	//constructor to initialize all fields
	public Ball(int size) {
		xPos = 400;
		yPos = 350;
		
		//random start movement
		xSpeed = (Math.random() *2 + 2) * direction();
		ySpeed = (Math.random() *2 + 2) * direction();
		
		//forces an even sized ball
		this.size = (size*2)/2;
	}
	
	//moves the ball and bounces it off top and bottom boundaries if it collides
	public void move () {
		xPos = xPos + xSpeed;
		yPos = yPos + ySpeed;
		
		if (yPos < size/2) {
			ySpeed = -ySpeed;
		}
		else if (yPos > (700 - size/2)) {
			ySpeed = -ySpeed;
		}
	}
	
	//draws the ball
	public void render(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillOval((int) xPos - (size/2), (int) yPos- (size/2), size, size);
		
	}
	
	//senses a hit by one of the paddles, and inverts the ball's speed and direction
	public void hit(Paddle player, Paddle opponent) {
		if (xPos <= 50 && xPos>= 30) {
			if (yPos >= player.position() && yPos <= player.position() + 80) {
				xSpeed = -xSpeed;
			}
		}
		else if (xPos >= 750 && xPos <= 770) {
			if (yPos >= opponent.position() && yPos <= opponent.position() + 80) {
				xSpeed = -xSpeed;
			}
		}
	}
	
	//getter for the x position of the ball
	public int xPos() {
		return (int) xPos;
	}
	
	//getter for the y position of the ball
	public int yPos() {
		return (int) yPos;
	}
	
	//random number generator (1 or 2) which will determine the player the ball moves towards first
	public int direction() {
		int direction = (int) (Math.random() * 2);
		if (direction == 1) {
			return 1;
		}
		else return -1;
	}
}
