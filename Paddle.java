import java.awt.Graphics;

public interface Paddle {
	public void render(Graphics graphics);
	public int position();
	public void move();
}
