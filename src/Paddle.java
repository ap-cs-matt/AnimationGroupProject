import java.awt.*;

public class Paddle {

	int x,y;
	protected static final int WIDTH = 60;
	protected static final int HEIGHT = 10;
	private Color color;

	public Paddle(doublePoint location, Color color) {
	
		x = location.getIntX();
		y = location.getIntY();
		this.color = color;
	}

	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}
