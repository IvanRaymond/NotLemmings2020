package l3.lemmings;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import l3.lemmings.model.Block;
import l3.lemmings.model.Lemming;
import l3.lemmings.model.Models;
import l3.lemmings.view.Views;

public class App {

	public final static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Lemming> lemmings = new ArrayList<Lemming>();
		ArrayList<Block> blocks = new ArrayList<Block>();
		Views view = new Views(new Models(),WIDTH-200, HEIGHT-100, lemmings, blocks);
		JFrame frame = new JFrame("Lemmingway");
		frame.add(view);
		frame.setSize(view.getSize());
		frame.setLocation(WIDTH/2-view.getSize().width/2, HEIGHT/2-view.getSize().height/2);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lemmings.add(new Lemming(10,15));
		for(int i = 10; i<25;i++)
			blocks.add(new Block(i,16));
		while (true) {
			view.update();
			view.repaint();
			Thread.sleep(900);
		}
		
	}

}
