package l3.lemmings.observable.lemming;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.direction.DirHorizontal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Keeps an array of the lemming surrounding elements
 */
public class Surrounding {

    private boolean blockOnTop;
    private boolean blockAhead;
    private boolean blockUnderRight;
    private boolean blockUnderLeft;
    private boolean wallAhead;
    private boolean floor;
    private boolean pitLow;
    private boolean pitHigh;
    private boolean blockSides;
    private boolean twoBlocksAhead;

    private boolean[][] surrounding;
    private ArrayList<IElement> elements;
    private Lemming l;

    public Surrounding(ArrayList<IElement> elements, Lemming l) {
        this.elements = elements;
        this.l = l;
        init(elements, l);
        scan(l);
    }

    /**
     * Initialize the array surrounding with position of climbable surrounding blocks
     * @param elements Array of game elements
     * @param l
     */
    private void init(ArrayList<IElement> elements, Lemming l) {

        surrounding = new boolean[7][5];
        Point lP = l.getPosition();

        for (IElement b : elements) {
            if (b.compare(Type.CLIMBABLE)) {
                Point bP = b.getPosition();

                // Block on Top
                if (bP.getX() == lP.getX() && bP.getY() == lP.getY() - 1) {
                    surrounding[0][2] = true;
                }

                // Blocks under
                if (bP.getX() == lP.getX() && bP.getY() <= lP.getY() + 5 && bP.getY() >= lP.getY() + 1) {
                    surrounding[(int) (bP.getY() - lP.getY()) + 1][2] = true;
                }

                // Blocks Ahead
                if ((bP.getX() == lP.getX() + 1 || bP.getX() == lP.getX() - 1)
                        && (bP.getY() == lP.getY() || bP.getY() == lP.getY() - 1)) {
                    surrounding[(int) (bP.getY() - lP.getY()) + 1][(int) (bP.getX() - lP.getX()) + 2] = true;
                }

                // Two Blocks Ahead
                if ((bP.getX() == lP.getX() + 2 || bP.getX() == lP.getX() - 2)
                        && (bP.getY() == lP.getY() || bP.getY() == lP.getY() - 1)) {
                    surrounding[(int) (bP.getY() - lP.getY()) + 1][(int) (bP.getX() - lP.getX()) + 2] = true;
                }

                if (((bP.getX() == lP.getX()-1 || bP.getX() == lP.getX()+1)) && bP.getY() == lP.getY()){
                    surrounding[2][(int) (bP.getX()-lP.getX())+1] = true;
                }
            }
        }
    }

    /**
     * Scan the lemming's surrounding to detect cases
     * @param l
     */
    private void scan(Lemming l) {

        blockOnTop = false;
        blockAhead = false;
        wallAhead = false;
        floor = false;
        pitLow = false;
        pitHigh = false;
        blockUnderRight = false;
        blockUnderLeft = false;
        blockSides = false;
        twoBlocksAhead = false;

        if (surrounding[0][2]) {
            blockOnTop = true;
        }
        if (surrounding[2][2]) {
            floor = true;
        } else if (surrounding[3][2] || surrounding[4][2] || surrounding[5][2]) {
            pitLow = true;
        } else {
            pitHigh = true;
        }
        if (surrounding[1][3] && !surrounding[0][3] || surrounding[1][1] && !surrounding[0][1]) {
            blockSides = true;
        }
        if ((surrounding[1][3] && !surrounding[0][3] && l.getDirection().isGoing(DirHorizontal.RIGHT))
                || (surrounding[1][1] && !surrounding[0][1] && l.getDirection().isGoing(DirHorizontal.LEFT))) {
            blockAhead = true;
        }
        if ((surrounding[1][3] && surrounding[0][3] && l.getDirection().isGoing(DirHorizontal.RIGHT))
                || (surrounding[1][1] && surrounding[0][1] && l.getDirection().isGoing(DirHorizontal.LEFT))) {
            wallAhead = true;
        }
        if((surrounding[1][4] && l.getDirection().isGoing(DirHorizontal.RIGHT))
                || surrounding[1][0] && l.getDirection().isGoing(DirHorizontal.LEFT)){
            twoBlocksAhead = true;
        }
        if (surrounding[2][1] || surrounding[2][3]){
            blockUnderRight = true;
            blockUnderLeft = true;
        }
    }

    public void update() {
        init(elements, l);
        scan(l);
    }

    public boolean isBlockOnTop() {
        return blockOnTop;
    }

    public boolean isBlockAhead() {
        return blockAhead;
    }

    public boolean isWallAhead() {
        return wallAhead;
    }

    public boolean isFloor() {
        return floor;
    }

    public boolean isPitLow() {
        return pitLow;
    }

    public boolean isPitHigh() {
        return pitHigh;
    }

    public boolean isBlockSides(){
        return blockSides;
    }

    public boolean isTwoBlocksAhead(){
        return twoBlocksAhead;
    }

    public boolean isBlockUnderRight(){
        return blockUnderRight;
    }

    public boolean isBlockUnderLeft() {
        return blockUnderLeft;
    }
}
