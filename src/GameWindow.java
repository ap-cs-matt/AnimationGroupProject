import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameWindow extends JPanel {

	protected static JFrame mainFrame;
	protected Color backgroundColor;
	protected JToolBar topToolBar;
	boolean gameRunning = true;

	protected int x, y, width, height, speed = 0;
	protected Ball ball;
	protected ArrayList<Paddle> paddles = new ArrayList<Paddle>();

	Paddle Paddle1;
	Rectangle leftEdge, rightEdge;

	public GameWindow() throws AWTException, InterruptedException {

		// /window

		initMainFrame();
		initTopToolBar(); // manages appearance & buttons in the toolbar
		mainFrame.getContentPane().add(topToolBar, BorderLayout.NORTH); // adds_toolbar
		initScore();
		mainFrame.add(this);
		mainFrame.setVisible(true);

		initListeners();

		// moving graphics
		initPaddles();
		moveBall();
	}

	public void gameOver() {
		gameRunning = false;
	}
	public void gameRestart(){
		gameRunning = true;
		ball.setLocation(0,0);
		initScore(); // resets to zero
		
		paddles.clear();
		initPaddles();
	}

	double startTime;
	int score;

	private void initScore() {
		startTime = System.currentTimeMillis();

	}

	private void updateScore() {
		score = (int) (System.currentTimeMillis() - startTime) / 100;
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		

		if (gameRunning) {
			for (Paddle paddle : paddles) {
				paddle.paint(g2d);
			}
			ball.paint(g2d);
			///
			updateScore();
			g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 150));
			g2d.drawString(Integer.toString(score), this.getWidth() - 350,
					(int) this.getBounds().getMinY() + 100);
			/////
		} else{
			
			///prints game over
			g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 150));
			g.drawString("Game OVER", this.getWidth() / 2 - 400, this.getHeight() / 2);
			
			//pauses score
			g2d.drawString(Integer.toString(score), this.getWidth() - 350,
					(int) this.getBounds().getMinY() + 100);
		}
	}

	private void initPaddles() {
		// Paddle1 = new Paddle(new doublePoint(100, 250), Color.GREEN);
		for (int i = 0; i < 100; i++) {
			int randomX = (int) (Math.random() * this.getWidth());
			int randomY = (int) (Math.random() * this.getHeight());

			Paddle paddle = (new Paddle(new doublePoint(randomX, randomY),
					new Color((int) (Math.random() * 0x1000000))));
			paddles.add(paddle);
			this.repaint();
		}

	}

	private void moveBall() throws InterruptedException, AWTException {
		ball = new Ball(this);

		while (true) {

			ball.move();
			for (Paddle paddle : paddles) {

			}

			this.repaint();
			Thread.sleep(10);
		}
	}

	private void initTopToolBar() {
		// toolbar
		topToolBar = new JToolBar();
		uiUtil.initToolbar(topToolBar, new doublePoint(1920, 35), Color.WHITE); // color/design

		// button
		JButton setBackgroundColorButton = new JButton("Set Background Color");
		uiUtil.setMaterialButton(setBackgroundColorButton, new Dimension(200,
				30));

		setBackgroundColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose a Color",
						Color.WHITE);

				setBackgroundColor(color);
			}
		});
		topToolBar.addSeparator(new Dimension(4, 0));
		topToolBar.add(setBackgroundColorButton);
		topToolBar.addSeparator(new Dimension(4, 0));
		
		// button
				JButton restart = new JButton("Restart");
				uiUtil.setMaterialButton(restart, new Dimension(200,
						30));

				restart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gameRestart();
					}
				});
		
				topToolBar.add(restart);
	}

	private void setBackgroundColor(Color color) {
		this.setBackground(color);
		backgroundColor = color;
	}

	private void initListeners() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				ball.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {

				ball.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private static void initMainFrame() {
		mainFrame = new JFrame();
		uiUtil.initFrame(mainFrame, "Game", new doublePoint(0, 0),
				new Dimension(1920, 1080));
	}

	public void update(Graphics g) {
		paint(g);
	}
}
