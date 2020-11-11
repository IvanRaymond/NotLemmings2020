package l3.lemmings.model;

import java.awt.*;
import java.util.ArrayList;

public class Teleporter {

    private int x1, y1, x2, y2;

    public Teleporter(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void reach(Level level){
        ArrayList<Lemming> lemmings = level.getLemmings();
        for (Lemming lemming : lemmings){
            if (lemming.getX() == x1 && lemming.getY() == y1){
                lemming.setX(x2);
                lemming.setY(y2);
            }
            else if (lemming.getX() == x2 && lemming.getY() == y2){
                lemming.setX(x1);
                lemming.setY(y1);
            }
        }
        level.setLemmings(lemmings);
    }

    public Point getPosition1(){
        return new Point(x1,y1);
    }

    public Point getPosition2(){
        return new Point(x2,y2);
    }

}
