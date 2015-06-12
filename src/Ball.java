import java.awt.*;
import java.awt.event.KeyEvent;

public class Ball {
	protected int x = 1, y = 1;
	protected int verticalSpeed = 5;
	protected int horizontalSpeed = 0;
	protected boolean keyPressed = false;
	protected int horizontalTimer = 0; // increases by 1 every 10 milliseconds
										// for letting the ball slide faster the
										// longer the button is held down
	protected int verticalTimer = 0; // is gravity
	protected int initHorizontalSpeed = 7;
	protected int regenCount = 0;
	
	protected GameWindow game;

	public Ball(GameWindow game) {
		this.game = game;
	}

	void move() throws AWTException, InterruptedException {

		// //makes the ball authomaticalls start falling
		if (verticalSpeed < 0) {
			verticalTimer++;
		}

		// resets horizontalTimer if key is released or increases if it is still
		// pressed down
		if (keyPressed) {
			horizontalTimer++;

		} else {
			horizontalTimer = 0;
		}
		// ///

		// bounce off the top wall
		if ((y + verticalSpeed < 0) && verticalSpeed < 0) {
			verticalSpeed *= -1;
			verticalTimer = 0;
		}
		// if it hits bottom
		if (y + verticalSpeed > game.getHeight() - 30) {
			game.gameOver();
		}
		// /////

		if (horizontalTimer >= 15) {
			if (horizontalSpeed > 0) {
				horizontalSpeed += 1;

			} else {
				horizontalSpeed -= 1;
			}
			horizontalTimer = 0;
		}

		if (collision()) {
			jump();
			game.updateScore(1 + game.getScore());
			verticalTimer = 0;
		}
		// gravity
		if (verticalTimer > 35 /* how soon you fall back */
				&& verticalSpeed < 0) {
			verticalSpeed = 5; // how fast you fall / rise
			verticalTimer = 0;
		}

		// /passthrough
		if (x + horizontalSpeed < 0) {
			x = game.getWidth() - 30;
		} else if (x + horizontalSpeed + 30 > game.getWidth()) {
			x = 0;
		}
		// ///
		x = x + horizontalSpeed;
		y = y + verticalSpeed;

	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {

		case (KeyEvent.VK_RIGHT): {
			keyPressed = true;
			if (horizontalSpeed <= 0) {
				horizontalSpeed = initHorizontalSpeed;
			}
			break;
		}

		case (KeyEvent.VK_LEFT): {
			keyPressed = true;
			if (horizontalSpeed >= 0) {
				horizontalSpeed = -initHorizontalSpeed;
			}
			break;
		}
		}
	}

	public void keyReleased(KeyEvent e) {

		if ((e.getKeyCode() == KeyEvent.VK_RIGHT)
				|| (e.getKeyCode() == KeyEvent.VK_LEFT)) {
			horizontalSpeed = 0;
			keyPressed = false;
		}
	}

	public void jump() throws AWTException, InterruptedException {

		verticalSpeed *= -1; // reverse direction
	}

	public void paint(Graphics2D g) {

		g.fillOval(x, y, 30, 30);
	}

	private boolean collision() {
		boolean intersects = false;
		int paddleIntersect = 0;

		for (Paddle paddle : game.paddles) {
			if (paddle.getBounds().intersects(getBounds()) && verticalSpeed > 0) {
				intersects = true;
			}
			if (!intersects) {
				paddleIntersect++;
			}
		}

		if (intersects){ //removes the paddle that was hit and adds  a new one
			game.paddles.remove(paddleIntersect);
			regenCount ++;
			if (regenCount > 1){
			int randomX = (int) (Math.random() * game.getWidth());
			int randomY = (int) (Math.random() * game.getHeight());

			Paddle paddle = (new Paddle(new doublePoint(randomX, randomY),
					new Color((int) (Math.random() * 0x1000000))));
			game.paddles.add(paddle);
			regenCount = 0;
			}
		}
		return intersects;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}
}
