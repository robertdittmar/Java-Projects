//class to represent a playable paddle, which implements the paddle interface

import java.awt.Color;
import java.awt.Graphics;

public class Player implements Paddle {
	//fields for position and speed, as well as acceleration 
	double xPos, yPos, speed;
	boolean moveUp, moveDn;
	
	//constructor that sets the initial values for all fields
	//depending on player number
	public Player(int number) {
		moveUp = false;
		moveDn = false;
		if (number == 1) {
			xPos = 20;
		}
		else if (number == 2) {
			xPos = 760;
		}
		yPos = 310;
		speed = 0.0;
			
	}
	
	//renders the player's graphic
	public void render(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect((int) xPos, (int) yPos, 20, 80);
		
	}
	//changes paddle's upward movement status
	public void moveUp(boolean up) {
		moveUp = up;
	}
	//changes paddle's downward movement status
	public void moveDown(boolean down) {
		moveDn = down;
	}

	//returns the vertical height of the paddle
	public int position() {
		return (int) yPos;
	}

	//facilitates player movement
	public void move() {
		//causes paddle to speed up over time upward
		if (moveUp) {
			speed -= 3;
		}
		//causes paddle to speed up over time downward
		else if (moveDn) {
			speed += 3;
		}
		//causes paddle to move slower over time
		else if (!moveUp && !moveDn) {
			speed = (speed*0.5);
		}
		//speed cap
		if (speed > 6) {
			speed = 6;
		}
		//speed cap
		else if (speed < -6) {
			speed = -6;
		}
		//enforces upper boundary
		if (yPos < 0) {
			yPos = 0;
		}
		//enforces lower boundary
		if (yPos > 620) {
			yPos = 620;
		}
		//alters the paddle's position by the paddle's speed based on user input
		yPos = yPos + speed;
	}
}
