package l3.lemmings.observer;

import l3.lemmings.observable.lemming.ILemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;
import l3.lemmings.observer.Listener;
import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Game;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.state.Activity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Views extends JComponent implements IObserver {
    /**
     *
     */
    private static final long serialVersionUID = 2493529223813614525L;

    enum currentDisplay {
        mainMenu,
        game
    }

    private final ArrayList<IElement> elements;
    private ArrayList<LemmingObservable> lemmings;

    private final int numberOfButtons = 12;
    private final int singleButtonSize = 2;
    private final int numCaseX;
    private final int numCaseY;
    private final int w;
    private final int h;
    private final int blockWidth;
    private final int blockHeight;

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
    final BufferedImage spriteSwitch = ImageIO.read(new File("resource/images/spriteSwitch.jpg"));


    // View should access controller and not games.
    public Views(Game game, int w, int h, int x, int y) throws IOException {
        numCaseX = x;
        numCaseY = y;
        this.w = w;
        this.h = h;
        Listener listener = new Listener(this, game);
        MouseAdapter ma = listener;
        addMouseListener(ma);
        setOpaque(true);
        setSize(w, h);

        elements = game.getLevel().getElements();
        lemmings = game.getLevel().getLemmings();

        // ToDo Add view to all lemmings observers list
        for(LemmingObservable l : lemmings){
            l.register(this);
        }

        blockWidth = getSize().width / numCaseX;
        blockHeight = getSize().height / numCaseY;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        drawMoves(g);
    }

    @Override
    public void update() {
        System.out.println("Is stepping");
        repaint();
    }

    private void drawMoves(Graphics g) {

        for (int i = 0; i < numCaseX; i++)
            for (int j = 0; j < numCaseY - 2; j++)
                drawMatrix(g, i, j);

        drawButtons(g);

        g.setColor(new Color(112, 72, 60));
        for (IElement e : elements) {
            if (e.compare(Type.BLOCK)) {
                g.drawImage(spriteBlock, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            }
            if(e.compare(Type.BOMB))
                g.drawImage(spriteTrap, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth,blockHeight,null);
            if (e.compare(Type.LAVA))
                g.drawImage(spriteLava, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            if(e.compare(Type.TELEPORTER))
            {
                g.drawImage(spritePortal, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
                g.drawImage(spritePortal, (int) e.getSecondPosition().getX() * blockWidth, (int) e.getSecondPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            }
            if (e.compare(Type.STEP))
                g.drawImage(spriteStair, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            if (e.compare(Type.ENTRANCE))
                g.drawImage(spriteEntrance, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            if (e.compare(Type.ESCAPE))
                g.drawImage(spriteExit, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
            if (e.compare(Type.SWITCH))
                g.drawImage(spriteSwitch, (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
        }
        for(LemmingObservable l : lemmings){
            if (l.compare(Type.LEMMING))
            {
                g.setColor(l.getColor());
               g.fillRect((int)l.getPosition().getX() * blockWidth, (int)l.getPosition().getY() * blockHeight, blockWidth, blockHeight);
            }
                //g.drawImage(l.getColor(), (int) l.getPosition().getX() * blockWidth, (int) l.getPosition().getY() * blockHeight, blockWidth, blockHeight, null);
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
        Point p1 = new Point(0, h - (singleButtonSize * blockHeight));
        Point p2 = new Point(0, h);
        for (int i = 0; i <= numberOfButtons; i++) {
            //g.drawImage(lemmingSprite(l), l.getX() * blockWidth, l.getY() * blockHeight, blockWidth,blockHeight,null);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1.x = p1.x + (blockWidth * singleButtonSize);
            p2.x = p1.x;
        }
        g.drawLine(0, h - (singleButtonSize * blockHeight), w, h - (singleButtonSize * blockHeight));
        g.drawImage(spriteClimber, 4 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteFloater, 6 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteBomb, 8 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteBlocker, 10 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteBuilder, 12 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteBasher, 14 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteMiner, 16 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
        g.drawImage(spriteDigger, 18 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 3, blockWidth, blockHeight, null);
    }

    // ToDo replace with something more OOP
    private BufferedImage lemmingSprite(LemmingObservable l) {

        if (l.state().isState(Activity.NORMAL)) {
            return spriteNormal;
        } else if (l.state().isState(Activity.CLIMBER)) {
            return spriteClimber;
        } else if (l.state().isState(Activity.FLOATER)) {
			return spriteFloater;
		}
		else if (l.state().isState(Activity.BOMBER)) {
			return spriteBomb;
		}
		else if (l.state().isState(Activity.BLOCKER)) {
			return spriteBlocker;
		}
		else if (l.state().isState(Activity.BRIDGE_BUILDER)) {
			return spriteNormal;
		}
		else if (l.state().isState(Activity.DIGGER)) {
			return spriteDigger;
		}
		else if (l.state().isState(Activity.BASHER)) {
			return spriteBasher;
		}
		else if (l.state().isState(Activity.MINER)) {
			return spriteMiner;
		}
        return spriteNormal;
    }

    public int getSingleButtonSize() {
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

