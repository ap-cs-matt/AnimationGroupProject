import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	int x, y =  1;
	int xa, ya = 1;
	
	//for managing color and fade in & out
	Color color = Color.BLACK;
	int alpha = 0;
	boolean alphaIncreasing = false;
	
	private GameWindow game;

	public Ball(GameWindow game) {
		this.game = game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;	
		if (x + xa > game.getWidth() - 30)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 30)
			ya = -1;

		
		x = x + (xa);
		y = y + (ya);
	}

	public void paint(Graphics2D g) {
		g.setColor(getColor());
		g.fillOval(x, y, 30, 30);
	}
	
	public doublePoint getLocation(){
		return new doublePoint(x,y);
	}
	private Color getColor(){
		
		//handles fade in and out
		if (alpha == 50 || alphaIncreasing) {
			alphaIncreasing = true;
			alpha += 2;
			if (alpha > 255) {
				alpha = 255;

			}
		}
		if (alpha == 255 || (!alphaIncreasing)) {
			alphaIncreasing = false;
			alpha -= 2;
			if (alpha < 50) {
				alpha = 50;

			}
		}
		
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	public void changeDirection(){
		
		if (this.xa > 0){
			this.xa = -1;
		}
		else{
			this.xa = 1;
		}
		if (this.ya > 0){
			this.ya = 1;
		}
		else {
			this.ya = -1;
		}
	}
}
