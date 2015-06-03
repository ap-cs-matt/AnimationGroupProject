import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements MouseListener,
		MouseMotionListener {

	protected static JFrame mainFrame;
	protected Color backgroundColor;
	protected JToolBar topToolBar;
	protected int x, y, width, height, alpha = 0;
	protected boolean alphaIncreasing = false;
	protected Image virtualMem;
	protected Graphics gBuffer;
	protected Ball ball, ball2;
	

	public GameWindow() throws AWTException, InterruptedException {
		ball = new Ball(this, 1);
		ball2 = new Ball(this, 2);

		initMainFrame();
		initTopToolBar(); // manages appearance & buttons in the toolbar

		mainFrame.getContentPane().add(topToolBar, BorderLayout.NORTH); // adds_toolbar

		initMouseListeners();

		mainFrame.add(this);
		mainFrame.setVisible(true);
		
		while (true){
			
			ball.move();
			ball2.move();
			this.repaint();
			new Robot().delay(10);
			//Thread.sleep(5);

		}

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(x, y, 20, 20);
		
		if (alpha == 0 || alphaIncreasing){
			alphaIncreasing = true;
			alpha += 2;
			if (alpha > 255){
				alpha = 255;
			
			}
		}
		if (alpha == 255 || (!alphaIncreasing)){
			alphaIncreasing = false;
			alpha -= 2;
			if (alpha < 0){
				alpha = 0;
			
			}
			
		}
		
		
		g2d.setColor(new Color(48,35,222,alpha));
		ball.paint(g2d);
		
		
		g2d.setColor(new Color(122,39,169,alpha));
		ball2.paint(g2d);

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