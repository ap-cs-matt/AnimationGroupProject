package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle {

	private static final int x = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int y = 0;
	int ya = 0;
	private GameWindow game;

	public Paddle(GameWindow game, doublePoint location, Color color) {
		this.game = game;
	}

	public void move() {
		if (y + ya > 0 && x + ya < game.getWidth() - WIDTH)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			ya = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			ya = 1;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getTopY() {
		return y;
	}

	public boolean Inside(doublePoint doublePoint) {
		// TODO Auto-generated method stub
		return false;
	}
}
