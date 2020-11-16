package l3.lemmings.model.lemming;

import l3.lemmings.model.Element;
import l3.lemmings.model.Type;
import l3.lemmings.model.lemming.direction.DirHorizontal;

import java.awt.*;
import java.util.ArrayList;

/**
 * Keeps an array of the lemming surrounding elements
 */
public class Surrounding {

    private boolean blockOnTop;
    private boolean blockAhead;
    private boolean wallAhead;
    private boolean floor;
    private boolean pitLow;
    private boolean pitHigh;

    private boolean[][] surrounding;

    private boolean stairAhead;

    public Surrounding(ArrayList<Element> elements, Lemming l){
        init(elements, l);
    }

    private void init(ArrayList<Element> elements, Lemming l){

        surrounding = new boolean[7][3];
        Point lP = l.getPosition();

        for(Element b : elements) {
            if (b.compare(Type.BLOCK) || b.compare(Type.SWITCH) || b.compare(Type.BOMB)){
                Point bP = b.getPosition();

                // Block on Top
                if (bP.getX() == lP.getX()-1 && bP.getY() == lP.getY()){
                    surrounding[0][1] = true;
                }

                // Blocks under
                if (bP.getX() == lP.getX() && bP.getY() <= lP.getY()+5 && bP.getY() >= lP.getY()+1){
                    surrounding[(int) (bP.getY()-lP.getY())+1][1] = true;
                }

                // Block Ahead
                if (((l.getDirection().isGoing(DirHorizontal.RIGHT) && bP.getX() == lP.getX()+1) ||
                        (l.getDirection().isGoing(DirHorizontal.LEFT) && bP.getX() == lP.getX()-1))
                        && (lP.getY() == bP.getY() || bP.getY() == lP.getY()-1)){
                    surrounding[(int) (bP.getY()-lP.getY())+1][(int) (bP.getX()-lP.getX())+1] = true;
                }

            }
        }
    }

    private void scan(ArrayList<Element> elements, Lemming l){

    }

    public boolean isBlockOnTop(){
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
}
