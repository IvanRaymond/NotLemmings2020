package l3.lemmings;

import java.awt.Toolkit;
import javax.swing.JFrame;

import l3.lemmings.model.Level;
import l3.lemmings.view.Views;

public class App {

	public final static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	private final static int    MAX_FPS = 50;
	// maximum number of frames to be skipped
	private final static int    MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int    FRAME_PERIOD = 30000 / MAX_FPS;

	public static void main(String[] args) throws InterruptedException {

		int numCaseX = 40;
		int numCaseY = 24;

		Views view = new Views(new Level(),WIDTH-200, HEIGHT-100, numCaseX, numCaseY);
		JFrame frame = new JFrame("Lemmingway");
		frame.add(view);
		frame.setSize(view.getSize());
		frame.setLocation(WIDTH/2-view.getSize().width/2, HEIGHT/2-view.getSize().height/2);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		while (true) {
			view.update();
			view.repaint();
			Thread.sleep(900);
		}
	}

}
