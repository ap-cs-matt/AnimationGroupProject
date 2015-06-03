import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements MouseListener,
		MouseMotionListener {

	protected static JFrame mainFrame;
	protected Color backgroundColor;
	protected JToolBar topToolBar;
	protected int x, y, width, height, alpha, speed = 0;
	protected boolean alphaIncreasing = false;
	// protected Image virtualMem;
	// protected Graphics gBuffer;
	protected Ball ball, ball2;
	Paddle leftPaddle, rightPaddle;
	Rectangle leftEdge, rightEdge;

	public GameWindow() throws AWTException, InterruptedException {

		initMainFrame();
		initTopToolBar(); // manages appearance & buttons in the toolbar
		initPaddles();
		initEdgeDetectors();

		mainFrame.getContentPane().add(topToolBar, BorderLayout.NORTH); // adds_toolbar

		initMouseListeners();

		mainFrame.add(this);
		mainFrame.setVisible(true);

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

		if (leftEdge.contains(ball.getLocation().getIntX(), ball.getLocation()
				.getIntY())
				|| leftEdge.contains(ball2.getLocation().getIntX(), ball2
						.getLocation().getIntY())) {
			System.out.println("Game over");
			g2d.setFont(new Font("Arial", Font.BOLD, 240));
			g2d.drawString("Game over", mainFrame.getWidth() / 2,
					mainFrame.getHeight() / 2);

		}

		if (rightPaddle.Inside(new doublePoint(ball.getLocation().getX() + 30,
				ball.getLocation().getY() + 30))) { // acounting for the ball
													// exmpading from location
													// point
			ball.changeDirection(); // makes the balls move right
			System.out.println("Deflected");
		}
		if (leftPaddle.Inside(ball.getLocation())) {
			ball.changeDirection(); // makes the balls move right
			System.out.println("Deflected");
		}

		// /drawing the moving balls
		g2d.setColor(new Color(48, 35, 222, alpha));
		//g2d.setColor(new Color(48, 35, 22));
		ball.paint(g2d);

		g2d.setColor(new Color(122, 39, 169, alpha));
		// ball2.paint(g2d);

	}

	private void initEdgeDetectors() {
		leftEdge = new Rectangle(0, 0, 1, mainFrame.getHeight()); // draws a
																	// single
																	// pixel
																	// wide
																	// barrier
		rightEdge = new Rectangle(1400, 0, 1, mainFrame.getHeight());

	}

	private void initPaddles() {
		leftPaddle = new leftPaddle(this, new doublePoint(0, 35),
				new doublePoint(20, 100), Color.BLUE);
		rightPaddle = new rightPaddle(this, new doublePoint(1400, 0),
				new doublePoint(20, 1050), Color.RED);
		this.repaint();
	}

	private void animateBalls() throws InterruptedException {
		ball = new Ball(this, 1);
		ball2 = new Ball(this, 2);

		while (true) {

			speed = 15;
			
				ball.move(speed);
			
			
			// ball2.move();
			this.repaint();
			Thread.sleep(15);

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

	private void initMouseListeners() {
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	private static void initMainFrame() {
		mainFrame = new JFrame();
		uiUtil.initFrame(mainFrame, "Game", new doublePoint(0, 0),
				new Dimension(1920, 1080));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		this.x = MouseInfo.getPointerInfo().getLocation().x;
		this.y = MouseInfo.getPointerInfo().getLocation().y - 80;
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void update(Graphics g) {
		paint(g);
	}
}
