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
            g.setColor(e.getColor());
            g.fillRect( (int) e.getPosition().getX() * blockWidth, (int) e.getPosition().getY() * blockHeight, blockWidth, blockHeight);
        }
        for(LemmingObservable l : lemmings){
            g.setColor(l.getColor());
            g.fillRect( (int) l.getPosition().getX() * blockWidth, (int) l.getPosition().getY() * blockHeight, blockWidth, blockHeight);
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
        g.drawString("Climber", 4 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Floater", 6 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Bomb", 8 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Blocker", 10 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Builder", 12 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Basher", 14 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Miner", 16 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
        g.drawString("Digger", 18 * blockWidth + 3, h - (singleButtonSize * blockHeight) + 15);
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

