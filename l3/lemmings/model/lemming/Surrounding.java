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
    ArrayList<Element> elements;
    Lemming l;

    private boolean stairAhead;

    public Surrounding(ArrayList<Element> elements, Lemming l){
        this.elements = elements;
        this.l = l;
        init(elements, l);
        scan(l);
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

                // Blocks Ahead
                if ((bP.getX() == lP.getX()+1 || bP.getX() == lP.getX()-1)
                        && (lP.getY() == bP.getY() || bP.getY() == lP.getY()-1)){
                    surrounding[(int) (bP.getY()-lP.getY())+1][(int) (bP.getX()-lP.getX())+1] = true;
                }
            }
        }
    }

    private void scan(Lemming l){

        blockOnTop = false;
        blockAhead = false;
        wallAhead = false;
        floor = false;
        pitLow = false;
        pitHigh = false;

        if(surrounding[0][1]){
            blockOnTop = true;
        }
        if(surrounding[2][1]){
            floor = true;
        }
        else if (surrounding[3][1] || surrounding[4][1] || surrounding[5][1]){
            pitLow = true;
        }
        else {
            pitHigh = true;
        }
        if((surrounding[1][2] && !surrounding[0][2] && l.getDirection().isGoing(DirHorizontal.RIGHT))
        || (surrounding[1][0] && !surrounding[0][0] && l.getDirection().isGoing(DirHorizontal.LEFT))){
            blockAhead = true;
        }
        if((surrounding[1][2] && surrounding[0][2] && l.getDirection().isGoing(DirHorizontal.RIGHT))
                || (surrounding[1][0] && surrounding[0][0] && l.getDirection().isGoing(DirHorizontal.LEFT))){
            wallAhead = true;
        }
    }

    public void update(){
        init(elements, l);
        scan(l);
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
