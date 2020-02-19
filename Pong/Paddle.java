//interface to allow both players and AI to play the game more easily

import java.awt.Graphics;

public interface Paddle {
	public void render(Graphics graphics);
	public int position();
	public void move();
}
