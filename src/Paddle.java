import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Paddle {

	Rectangle paddle;
	doublePoint size, location;
	GameWindow window;
	Color color;

	public Paddle(GameWindow window, doublePoint location, doublePoint size,
			Color color) {
		this.window = window;
		this.location = location;
		this.size = size;
		this.color = color;

		// build rectangle
		paddle = new Rectangle(location.getIntX(), location.getIntY(),
				size.getIntX(), size.getIntY());

	}

	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillRect(location.getIntX(), location.getIntY(), size.getIntX(),
				size.getIntY());
	}

	public void movePaddle(String direction){
		switch (direction){
		case "down" : location.setPoints(location.getX(), location.getY() + 10);
			break;
		case "up" : location.setPoints(location.getX(), location.getY() + - 10);
		}
		
	}

	public boolean Inside(doublePoint location) {

		if (paddle.contains(location.getIntX(), location.getIntY())) {
			System.out.println("testing inside");
			return true;
		} else {
			// System.out.println("testing inside");
			return false;
		}

	}
}

// ////left paddle usees w,s keys
class leftPaddle extends Paddle {

	public leftPaddle(GameWindow window, doublePoint location,
			doublePoint size, Color color) {
		super(window, location, size, color);
		KeyListener listener = new MyKeyListener();
		window.addKeyListener(listener);
		window.setFocusable(true);
	}

	public boolean Inside(doublePoint location) {

		if (paddle.contains(location.getIntX(), location.getIntY())) {
			System.out.println("testing inside");
			return true;
		} else {
			// System.out.println("testing inside");
			return false;
		}

	}

	class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {

				System.out.print("down");

				location.setPoints(location.getX(), location.getY() + 30);

			}
			if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.print("up");
				location.setPoints(location.getX(), location.getY() - 30);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

}

class rightPaddle extends Paddle {

	public rightPaddle(GameWindow window, doublePoint location,
			doublePoint size, Color color) {
		super(window, location, size, color);
		// KeyListener listener = new MyKeyListener();
		// window.addKeyListener(listener);
		// window.setFocusable(true);
	}

	public boolean Inside(doublePoint location) {

		if (paddle.contains(location.getIntX(), location.getIntY())) {
			System.out.println("testing inside");
			return true;
		} else {
			// System.out.println("testing inside");
			return false;
		}

	}

	class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {

				System.out.print("down");
				location.setPoints(location.getX(), location.getY() + 15);

			}
			if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.print("up");
				location.setPoints(location.getX(), location.getY() - 15);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		public boolean Inside(doublePoint location) {

			if (paddle.contains(location.getIntX(), location.getIntY())) {
				System.out.println("testing inside");
				return true;
			} else {
				// System.out.println("testing inside");
				return false;
			}

		}
	}
}
