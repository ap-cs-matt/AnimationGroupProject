import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GameWindow extends JPanel {

	protected static JFrame mainFrame;
	protected Color backgroundColor;
	protected JToolBar topToolBar;
	protected int x, y, width, height, alpha, speed = 0;
	protected boolean alphaIncreasing = false;
	protected Ball ball, ball2;
	Paddle leftPaddle, rightPaddle;
	Rectangle leftEdge, rightEdge;

	public GameWindow() throws AWTException, InterruptedException {

		// /window
		initMainFrame();
		initTopToolBar(); // manages appearance & buttons in the toolbar
		mainFrame.getContentPane().add(topToolBar, BorderLayout.NORTH); // adds_toolbar
		mainFrame.add(this);
		mainFrame.setVisible(true);

		// moving graphics
		initPaddles();
		initEdgeDetectors();

		initListeners();
		animateBalls();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// /drawing paddles
		leftPaddle.paint(g2d);
		rightPaddle.paint(g2d);

		ball.paint(g2d);
	}

	private void initEdgeDetectors() {
		leftEdge = new Rectangle(0, 0, 1, mainFrame.getHeight()); //barrier
		rightEdge = new Rectangle(1400, 0, 1, mainFrame.getHeight());

	}

	private void initPaddles() {
		leftPaddle = new Paddle(this, new doublePoint(20, 100), Color.BLUE);
		rightPaddle = new Paddle(this, new doublePoint(20, 1050), Color.RED);
		this.repaint();
	}

	private void animateBalls() throws InterruptedException {
		ball = new Ball(this);

		while (true) {
			
			ball.move(speed);

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

			}

			@Override
			public void keyPressed(KeyEvent e) {

				System.out.println("hfgghfghhfghgfhdaaa");
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
