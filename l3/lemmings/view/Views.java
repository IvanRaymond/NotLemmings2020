package l3.lemmings.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;

import l3.lemmings.controller.Listener;
import l3.lemmings.model.Block;
import l3.lemmings.model.Lemming;
import l3.lemmings.model.Models;
import l3.lemmings.model.Lemming.LemmingState;

import java.util.ArrayList;

public class Views extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2493529223813614525L;
		
	private final Models game;
	
	enum currentDisplay {
		  mainMenu,
		  game
	}

	ArrayList<Lemming> lemmings = new ArrayList<Lemming>();
	ArrayList<Block> blocks = new ArrayList<Block>();

	
	int numCaseX = 40;
	int numCaseY = 24;

	int w,h;

	int blockWidth;
	int blockHeight;


	public Views(Models m, int w, int h, ArrayList<Lemming> l, ArrayList<Block> b, int x, int y) {
		this.game = m;
		this.lemmings = l;
		this.blocks = b;
		numCaseX = x;
		numCaseY = y;
		MouseAdapter ma = new Listener(this);
		addMouseListener(ma);
		setOpaque(true);
		setSize(w, h);

		this.w = w;
		this.h = h;

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
			for(int j = 0; j < numCaseY;j++)
				drawMove(g,i,j);
		
		g.setColor(Color.BLACK);
		for(Block b : blocks)
			g.fillRect(b.getX() * blockWidth,b.getY() * blockHeight, blockWidth, blockHeight);

		for(Lemming l : lemmings) {
			g.setColor(lemmingAnimation(l));
			g.fillRect(l.getX() * blockWidth, l.getY() * blockHeight, blockWidth, blockHeight);
		}
	}
	
	private void drawMove(Graphics g, int posX, int posY) {
		int x = blockWidth * posX;
		int y = blockHeight * posY;
		g.setColor(Color.WHITE);	
		g.fillRect(x, y, blockWidth, blockHeight);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, blockWidth, blockHeight);
	}
	
	private Color lemmingAnimation(Lemming l) {
		
		if (l.getState()==LemmingState.NORMAL) {
			return Color.RED;
		}
		if (l.getState()==LemmingState.CLIMBER) {
			return Color.BLUE;
		}
		if (l.getState()==LemmingState.FLOATER) {
			return Color.CYAN;
		}
		if (l.getState()==LemmingState.BOMB) {
			return Color.DARK_GRAY;
		}
		if (l.getState()==LemmingState.BLOCKER) {
			return Color.GRAY;
		}
		if (l.getState()==LemmingState.BRIDGE_BUILDER) {
			return Color.GREEN;
		}
		if (l.getState()==LemmingState.BASHER) {
			return Color.MAGENTA;
		}
		if (l.getState()==LemmingState.MINER) {
			return Color.ORANGE;
		}
		return Color.PINK;
	}
	
	public boolean lemmingPresent(Point cell) {
		Lemming currentLemming;
		for (int i=0; i<lemmings.size(); i++) {
			currentLemming = lemmings.get(i);
			if (currentLemming.getX() == cell.x && currentLemming.getY() == cell.y) {
				return true;
			}
		}
		return false;
	}
	
	public Lemming getLemming(Point cell) {
		Lemming currentLemming;
		for (int i=0; i<lemmings.size(); i++) {
			currentLemming = lemmings.get(i);
			if (currentLemming.getX() == cell.x && currentLemming.getY() == cell.y) {
				return currentLemming;
			}
		}
		return null;
	}

	public void update() {
		int gg=0;
		for(Lemming l : lemmings)
		{
			boolean[][] surrounding = new boolean[3][3];
			for(Block b : blocks)
			{
				if(l.getY() == b.getY())
				{	if(l.getX() == b.getX())
					surrounding[1][1] = true;
				else if(l.getX() - 1 == b.getX() )
					surrounding[0][1] = true;
				else if(l.getX() + 1 == b.getX())
					surrounding[2][1] = true;}
				else if(l.getY() - 1 == b.getY())
				{
					if(l.getX() == b.getX())
						surrounding[1][0] = true;
					else if(l.getX() - 1 == b.getX())
						surrounding[0][0] = true;
					else if(l.getX() + 1 == b.getX())
						surrounding[2][0] = true;
				}
				else if(l.getY() + 1 == b.getY())
				{
					if(l.getX() == b.getX())
						surrounding[1][2] = true;
					else if(l.getX() - 1 == b.getX())
						surrounding[0][2] = true;
					else if(l.getX() + 1 == b.getX())
						surrounding[2][2] = true;
				}
			}
			if(surrounding[0][0] && surrounding[0][1] && l.getDirectionAxisX() == -1 || surrounding[2][0] && surrounding[2][1] && l.getDirectionAxisX() == 1)
			{
				l.setDirectionAxisX(l.getDirectionAxisX()*-1);
			}

			if(!surrounding[1][2])
			{
				l.setDirectionAxisY(1);
			}

			if(surrounding[1][2]){
				l.setDirectionAxisY(0);
			}

			if(surrounding[2][1] && !surrounding[2][0] && l.getDirectionAxisX() == 1 || surrounding[0][1] && !surrounding[0][0] && l.getDirectionAxisX() == -1)
			{
				l.setDirectionAxisY(-1);
			}


			for(int i =0;i<3;i++)
			{
				for(int j =0;j<3;j++)
					System.out.print(" "+surrounding[i][j]);
				System.out.println();
			}
			System.out.println();
			l.move();
		}

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

