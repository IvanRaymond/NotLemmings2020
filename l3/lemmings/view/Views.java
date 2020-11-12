package l3.lemmings.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import l3.lemmings.controller.Listener;
import l3.lemmings.model.*;
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
	private ArrayList<Trap> traps;
	private ArrayList<Staircase> staircases;
	private ArrayList<Lava> lava;
	private ArrayList<Entrance> entrances;
	private ArrayList<Escape> escapes;
	private ArrayList<Teleporter> teleporters;

	private int numberOfButtons = 12;
	private int singleButtonSize = 2;
	private int numCaseX,numCaseY;
	private int w,h;
	private int blockWidth,blockHeight;
	private Level level;


// 	// Comment out if breaks code, can't test without it
// 	final BufferedImage spriteNormal = ImageIO.read(this.getClass().getResource("/images/spriteNormal.jpg"));
// 	final BufferedImage spriteClimber = ImageIO.read(this.getClass().getResource("/images/spriteClimber.jpg"));
// 	final BufferedImage spriteFloater = ImageIO.read(this.getClass().getResource("/images/spriteFloater.jpg"));
// 	final BufferedImage spriteBomb = ImageIO.read(this.getClass().getResource("/images/spriteBomb.jpg"));
// 	final BufferedImage spriteBlocker = ImageIO.read(this.getClass().getResource("/images/spriteBlocker.jpg"));
// 	final BufferedImage spriteBuilder = ImageIO.read(this.getClass().getResource("/images/spriteBuilder.jpg"));
// 	final BufferedImage spriteBasher = ImageIO.read(this.getClass().getResource("/images/spriteBasher.jpg"));
// 	final BufferedImage spriteMiner = ImageIO.read(this.getClass().getResource("/images/spriteMiner.jpg"));
// 	final BufferedImage spriteDigger = ImageIO.read(this.getClass().getResource("/images/spriteDigger.jpg"));

	final BufferedImage spriteNormal = ImageIO.read(new File("resource/images/spriteNormal.jpg"));
	final BufferedImage spriteClimber = ImageIO.read(new File("resource/images/spriteClimber.jpg"));
	final BufferedImage spriteFloater = ImageIO.read(new File("resource/images/spriteFloater.jpg"));
	final BufferedImage spriteBomb = ImageIO.read(new File("resource/images/spriteBomb.jpg"));
	final BufferedImage spriteBlocker = ImageIO.read(new File("resource/images/spriteBlocker.jpg"));
	final BufferedImage spriteBuilder = ImageIO.read(new File("resource/images/spriteBuilder.jpg"));
	final BufferedImage spriteBasher = ImageIO.read(new File("resource/images/spriteBasher.jpg"));
	final BufferedImage spriteMiner = ImageIO.read(new File("resource/images/spriteMiner.jpg"));
	final BufferedImage spriteDigger = ImageIO.read(new File("resource/images/spriteDigger.jpg"));
	final BufferedImage spriteBlock = ImageIO.read(new File("resource/images/spriteBlock.jpg"));
	final BufferedImage spriteEntrance = ImageIO.read(new File("resource/images/spriteEntrance.jpg"));
	final BufferedImage spriteExit = ImageIO.read(new File("resource/images/spriteExit.jpg"));
	final BufferedImage spritePortal = ImageIO.read(new File("resource/images/spritePortal.jpg"));
	final BufferedImage spriteStair = ImageIO.read(new File("resource/images/spriteStair.jpg"));
	final BufferedImage spriteLava = ImageIO.read(new File("resource/images/spriteLava.jpg"));
	final BufferedImage spriteTrap = ImageIO.read(new File("resource/images/spriteTrap.jpg"));


	public Views(Game game, int w, int h, int x, int y) throws IOException {
		level = game.getLevel();
		this.lemmings = level.getLemmings();
		this.blocks = level.getBlocks();
		this.traps = level.getTraps();
		this.staircases = level.getStaircases();
		this.lava = level.getLava();
		this.teleporters = level.getTeleporters();
		this.entrances = level.getEntrances();
		this.escapes = level.getEscapes();
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
		
		g.setColor(new Color(112, 72, 60));
		for(Block b : blocks)
			g.drawImage(spriteBlock, (int) b.getX() * blockWidth, (int) b.getY() * blockHeight, blockWidth,blockHeight,null);

		for(Lemming l : lemmings)
			g.drawImage(lemmingSprite(l), (int) l.getX() * blockWidth, (int) l.getY() * blockHeight, blockWidth,blockHeight,null);

		for(Trap trap : traps)
			g.drawImage(spriteTrap, (int) trap.getX() * blockWidth, (int) trap.getY() * blockHeight, blockWidth,blockHeight,null);

		for(Lava lava : lava)
			g.drawImage(spriteLava, (int) lava.getX() * blockWidth, (int) lava.getY() * blockHeight, blockWidth,blockHeight,null);

		for(Teleporter teleporter : teleporters)
			g.drawImage(spritePortal, (int) teleporter.getPosition1().x * blockWidth, (int) teleporter.getPosition1().y * blockHeight, blockWidth,blockHeight,null);

		for(Staircase staircase : staircases){
			for(Staircase.Step step : staircase.getSteps())
				g.drawImage(spriteStair, (int) step.getX() * blockWidth, (int) step.getY() * blockHeight, blockWidth,blockHeight,null);
		}

		for(Entrance entrance : entrances)
			g.drawImage(spriteEntrance, (int) entrance.getX() * blockWidth, (int) entrance.getY() * blockHeight, blockWidth,blockHeight,null);

		for(Escape escape : escapes)
			g.drawImage(spriteExit, (int) escape.getX() * blockWidth, (int) escape.getY() * blockHeight, blockWidth,blockHeight,null);


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
			return spriteBasher;
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

