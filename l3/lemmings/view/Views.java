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
	private ArrayList<Switch> switches;

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
	final BufferedImage spriteBlock = ImageIO.read(new File("resource/images/spriteBlock.jpg"));
	final BufferedImage spriteEntrance = ImageIO.read(new File("resource/images/spriteEntrance.jpg"));
	final BufferedImage spriteExit = ImageIO.read(new File("resource/images/spriteExit.jpg"));
	final BufferedImage spritePortal = ImageIO.read(new File("resource/images/spritePortal.jpg"));
	final BufferedImage spriteStair = ImageIO.read(new File("resource/images/spriteStair.jpg"));
	final BufferedImage spriteLava = ImageIO.read(new File("resource/images/spriteLava.jpg"));
	final BufferedImage spriteTrap = ImageIO.read(new File("resource/images/spriteTrap.jpg"));
	final BufferedImage spriteSwitch = ImageIO.read(new File("resource/images/spriteSwitch.jpg"));


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
		this.switches = level.getSwitches();
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

		for(Block b : blocks)
		{
			g.setColor(new Color(47,79,79).darker());
			g.fillRect( (int) b.getX() * blockWidth, (int) b.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(new Color(47,79,79));
			g.fillRect( (int) b.getX() * blockWidth + 4, (int) b.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}
		for(Trap trap : traps)
		{
			g.setColor(Color.getHSBColor(136,100,49).darker());
			g.fillRect( (int) trap.getX() * blockWidth, (int) trap.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.getHSBColor(136,100,49));
			g.fillRect( (int) trap.getX() * blockWidth + 4, (int) trap.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}

		for(Lava lava : lava)
		{
			g.setColor(Color.red.darker());
			g.fillRect( (int) lava.getX() * blockWidth, (int) lava.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.red);
			g.fillRect( (int) lava.getX() * blockWidth + 4, (int) lava.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}

		for(Teleporter teleporter : teleporters)
		{
			g.setColor(Color.getHSBColor(264,100,88).darker());
			g.fillRect( (int) teleporter.getPosition1().x * blockWidth, (int) teleporter.getPosition1().y * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.getHSBColor(264,100,88));
			g.fillRect( (int) teleporter.getPosition1().x * blockWidth + 4, (int) teleporter.getPosition1().y * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}

		for(Staircase staircase : staircases){
			Color c = Color.getHSBColor(193,100,100);
			for(Staircase.Step step : staircase.getSteps())
			{
				Color c2 = c.darker();
				g.setColor(c2);
				g.fillRect( (int) step.getX() * blockWidth, (int) step.getY() * blockHeight, blockWidth, blockHeight);
				g.setColor(c);
				g.fillRect( (int) step.getX() * blockWidth + 4, (int) step.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
				c= c.darker();
			}
		}

		for(Entrance entrance : entrances)
		{
			g.setColor(Color.BLACK);
			g.fillRect( (int) entrance.getX() * blockWidth, (int) entrance.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.WHITE);
			g.fillRect( (int) entrance.getX() * blockWidth + 4, (int) entrance.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}

		for(Escape escape : escapes)
		{
			g.setColor(Color.GREEN.darker());
			g.fillRect( (int) escape.getX() * blockWidth, (int) escape.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.GREEN);
			g.fillRect( (int) escape.getX() * blockWidth + 4, (int) escape.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}
		for(Switch currentSwitch : switches)
		{
			g.setColor(Color.getHSBColor(156,100,88).darker());
			g.fillRect( (int) currentSwitch.getX() * blockWidth, (int) currentSwitch.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(Color.getHSBColor(156,100,100));
			g.fillRect( (int) currentSwitch.getX() * blockWidth + 4, (int) currentSwitch.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
		}

		for(Lemming l : lemmings)
		{
			g.setColor(lemmingSprite(l).darker());
			g.fillRect( (int) l.getX() * blockWidth, (int) l.getY() * blockHeight, blockWidth, blockHeight);
			g.setColor(lemmingSprite(l));
			g.fillRect( (int) l.getX() * blockWidth + 4, (int) l.getY() * blockHeight + 4, blockWidth - 8, blockHeight - 8);
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
		g.drawString("Climber", 4 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Floater", 6 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Bomb", 8 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Blocker", 10 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Builder", 12 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Basher", 14 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Miner", 16 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
		g.drawString("Digger", 18 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
	}

	private Color lemmingSprite(Lemming l) {
		
		if (l.getState()==LemmingState.NORMAL) {
			return Color.GRAY;
		}
		else if (l.getState()==LemmingState.CLIMBER) {
			return Color.YELLOW;
		}
		else if (l.getState()==LemmingState.FLOATER) {
			return Color.CYAN;
		}
		else if (l.getState()==LemmingState.BOMB) {
			return new Color(109, 14, 14);
		}
		else if (l.getState()==LemmingState.BLOCKER) {
			return Color.BLUE;
		}
		else if (l.getState()==LemmingState.BRIDGE_BUILDER) {
			return Color.YELLOW;
		}
		else if (l.getState()==LemmingState.DIGGER) {
			return Color.getHSBColor(13,100,43);
		}
		else if (l.getState()==LemmingState.BASHER) {
			return Color.getHSBColor(258,100,43);
		}
		else if (l.getState()==LemmingState.MINER) {
			return Color.getHSBColor(342,100,43);
		}
		return Color.darkGray;
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

