package l3.lemmings.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import l3.lemmings.controller.Listener;
import l3.lemmings.model.Block;
import l3.lemmings.model.Game;
import l3.lemmings.model.Lemming;
import l3.lemmings.model.Level;
import l3.lemmings.model.Lemming.LemmingState;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Views extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2493529223813614525L;

	enum currentDisplay {
		  mainMenu,
		  game
	}

	private ArrayList<Lemming> lemmings;
	private ArrayList<Block> blocks;

	private int numberOfButtons = 12;
	private int singleButtonSize = 2;
	private int numCaseX,numCaseY;
	private int w,h;
	private int blockWidth,blockHeight;
	private Level level;

	final BufferedImage spriteNormal = ImageIO.read(new File("resource/images/spriteNormal.jpg"));
	final BufferedImage spriteClimber = ImageIO.read(new File("resource/images/spriteClimber.jpg"));
	final BufferedImage spriteFloater = ImageIO.read(new File("resource/images/spriteFloater.jpg"));
	final BufferedImage spriteBomb = ImageIO.read(new File("resource/images/spriteBomb.jpg"));
	final BufferedImage spriteBlocker = ImageIO.read(new File("resource/images/spriteBlocker.jpg"));
	final BufferedImage spriteBuilder = ImageIO.read(new File("resource/images/spriteBuilder.jpg"));
	final BufferedImage spriteBasher = ImageIO.read(new File("resource/images/spriteBasher.jpg"));
	final BufferedImage spriteMiner = ImageIO.read(new File("resource/images/spriteMiner.jpg"));
	final BufferedImage spriteDigger = ImageIO.read(new File("resource/images/spriteDigger.jpg"));

	public Views(Game game, int w, int h, int x, int y) throws IOException {
		level = game.getLevel();
		this.lemmings = level.getLemmings();
		this.blocks = level.getBlocks();
		numCaseX = x;
		numCaseY = y;
		this.w = w;
		this.h = h;
		MouseAdapter ma = new Listener(this, game);
		addMouseListener(ma);
		setOpaque(true);
		setSize(w, h);

		blockWidth = getSize().width / numCaseX;
		blockHeight = getSize().height / numCaseY;

	}
	
	@Override
	public void paint(Graphics g) {	
		super.paint(g);
		setBackground(Color.WHITE);
		drawMoves(g);
		
	}

	private void drawMoves(Graphics g) {
		
		for(int i = 0; i < numCaseX;i++)
			for(int j = 0; j < numCaseY-2;j++)
				drawMatrix(g,i,j);

		drawButtons(g);
		
		g.setColor(Color.BLACK);
		for(Block b : blocks)
			g.fillRect(b.getX() * blockWidth,b.getY() * blockHeight, blockWidth, blockHeight);

		for(Lemming l : lemmings) {

			//g.setColor(lemmingSprite(l));
			//g.fillRect(l.getX() * blockWidth, l.getY() * blockHeight, blockWidth, blockHeight);
			g.drawImage(lemmingSprite(l), (int) l.getX() * blockWidth, (int) l.getY() * blockHeight, blockWidth,blockHeight,null);
		}
	}

	private void drawMatrix(Graphics g, int posX, int posY) {
		int x = blockWidth * posX;
		int y = blockHeight * posY;
		g.setColor(Color.WHITE);	
		g.fillRect(x, y, blockWidth, blockHeight);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, blockWidth, blockHeight);
	}

	private void drawButtons(Graphics g) {
		Point p1 = new Point(0,h-(singleButtonSize*blockHeight));
		Point p2 = new Point(0, h);
		for(int i=0; i<=numberOfButtons; i++){
			//g.drawImage(lemmingSprite(l), l.getX() * blockWidth, l.getY() * blockHeight, blockWidth,blockHeight,null);
			g.drawLine(p1.x,p1.y,p2.x,p2.y);
			p1.x = p1.x + (blockWidth*singleButtonSize);
			p2.x = p1.x;
		}
		g.drawLine(0,h-(singleButtonSize*blockHeight),w, h-(singleButtonSize*blockHeight));
		g.drawImage(spriteClimber, 4 * blockWidth+3, 	h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteFloater, 6 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteBomb, 8 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteBlocker, 10 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteBuilder, 12 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteBasher, 14 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteMiner, 16 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
		g.drawImage(spriteDigger, 18 * blockWidth+3, h-(singleButtonSize*blockHeight)+3, blockWidth,blockHeight,null);
	}

	private BufferedImage lemmingSprite(Lemming l) {
		
		if (l.getState()==LemmingState.NORMAL) {
			return spriteNormal;
		}
		if (l.getState()==LemmingState.CLIMBER) {
			return spriteClimber;
		}
		if (l.getState()==LemmingState.FLOATER) {
			return spriteFloater;
		}
		if (l.getState()==LemmingState.BOMB) {
			return spriteBomb;
		}
		if (l.getState()==LemmingState.BLOCKER) {
			return spriteBlocker;
		}
		if (l.getState()==LemmingState.BRIDGE_BUILDER) {
			return spriteNormal;
		}
		if (l.getState()==LemmingState.BASHER) {
			return spriteNormal;
		}
		if (l.getState()==LemmingState.MINER) {
			return spriteMiner;
		}
		return spriteNormal;
	}

	public int getSingleButtonSize(){
		return singleButtonSize;
	}

	public int getNumCaseX() {
		return numCaseX;
	}

	public int getNumCaseY() {
		return numCaseY;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
}

