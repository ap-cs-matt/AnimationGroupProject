import java.awt.Graphics2D;

public class Ball {
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private GameWindow game;

	public Ball(GameWindow game, int ballNumber) {
		this.game = game;

		switch (ballNumber) {
		case 1:
			x = 0;
			y = 0;
			xa = 1;
			ya = 1;
			break;
		case 2: 
			x = 200;
			y = 150;
			xa = 1;
			ya = 1;
		}
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

		x = x + xa;
		y = y + ya;
		//move();
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30, 30);
	}
	
	public doublePoint getLocation(){
		return new doublePoint(x,y);
	}
	public void changeDirection(){
		
		if (this.xa > 0){
			this.xa = -1;
		}
		else{
			this.xa = 1;
		}
		if (this.ya > 0){
			this.ya = -1;
		}
		else {
			this.ya = 1;
		}
	}

}
