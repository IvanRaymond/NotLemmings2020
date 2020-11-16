package l3.lemmings.model.trap;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.lemming.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class Lava implements Element {

    private Point point = new Point();

    public Lava(int x, int y){
        point.x = x;
        point.y = y;
    }

    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }

    @Override
    public Point getPosition() {
        return new Point(point);
    }

    @Override
    public Point getSecondPosition() {
        return null;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interact(Element element, Level level) {

        Lemming l = (Lemming) element;

        if (l.isAt(point) || l.isAt(new Point(point.x, point.y-1))){
                l.kill();
            }
        return true;
    }

}
