import java.awt.*;
import java.awt.geom.RoundRectangle2D;

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
		//g.fillRect(x, y, WIDTH, HEIGHT);
		g.fill(new RoundRectangle2D.Float(x, y, WIDTH, HEIGHT, 20, 20));
	}

	public void moveDown(int shift){
		this.y += shift;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setLocation(doublePoint location){
		this.x = location.getIntX();
		this.y = location.getIntY();
	}
}
