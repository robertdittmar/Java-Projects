//main driver class for Pong, which uses the classes Player, Paddle, and Ball to
//simulate the classic arcade game

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Pong_v1	extends Applet implements Runnable, KeyListener{

	//fields for the game dimensions, score, paddles, and ball, as well as a Thread to enable refreshes
	private final int width = 800, height = 700, size = 20;
	private Player player1, player2;
	private Thread thread;
	private Ball ball;
	private boolean playing;
	private int scorePlayer1, scorePlayer2;

	//constructor initializing all fields
	public void init() {
		player1 = new Player(1);
		player2 = new Player(2);
		ball = new Ball(size);
		scorePlayer1 = 0;
		scorePlayer2 = 0;
		playing = false;
		this.resize(width, height);
		this.addKeyListener(this);
		thread = new Thread(this);
		thread.start();
	}

	//this method is called in each iteration of the infinite loop in the run method, and
	//simply paints the screen with the necessary information
	public void paint(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);
		if (!playing) {
			graphics.setColor(Color.CYAN);
			graphics.drawString("Press Space to Play!", 335, 140);
		}
		//tests for a score
		if (ball.xPos() < -10) {
			scorePlayer1++;
		}
		else if (ball.xPos() > 810) {
			scorePlayer2++;
		}

		//if one player wins, display as such
		if (scorePlayer1 >= 11) {
			graphics.setColor(Color.GREEN);
			graphics.drawString("PLAYER 1 WINS!", 300, 100);
		}
		else if (scorePlayer2 >= 11) {
			graphics.setColor(Color.RED);
			graphics.drawString("PLAYER 2 WINS!", 300, 100);
		}

		//if not game over, continue the game
		else {
			graphics.setColor(Color.CYAN);
			graphics.drawString(scorePlayer1 + " - "+ scorePlayer2, 380, 200);
			player1.render(graphics);
			player2.render(graphics);
			ball.render(graphics);
		}
	}

	//calls paint to refresh the screen
	public void update(Graphics graphics) {
		paint(graphics);
	}

	//method to facilitate the game's action, with an infinite loop to play 
	//until the applet is closed
	public void run() {
		//infinite loop
		while (true) {
			//if game has been started, respond to key presses and start game
			if (playing) {
				player1.move();
				player2.move();
				ball.move();
				ball.hit(player1, player2);
			}
			repaint();

			//refresh
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//method necessary for KeyListener interface
	public void keyTyped(KeyEvent e) {

	}

	//facilitates movement of paddles, with w and s for player1, and up and down for player2
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player1.moveUp(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			player1.moveDown(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player2.moveUp(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.moveDown(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			playing = true;
		}
	}

	//facilitates end of movement of paddles, with w and s for player1, 
	//and up and down for player2	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player1.moveUp(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			player1.moveDown(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player2.moveUp(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.moveDown(false);
		}
	}

}
