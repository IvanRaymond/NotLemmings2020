package l3.lemmings.model.props;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;
import l3.lemmings.model.lemming.Lemming;

import java.awt.*;

public class Teleporter implements Element {

    private Point point1 = new Point();
    private Point point2 = new Point();

    public Teleporter(int x1, int y1, int x2, int y2){
        point1.x = x1;
        point1.y = y1;
        point2.x = x2;
        point2.y = y2;
    }

    public Teleporter(Point point1, Point point2){
        this.point1=new Point(point1);
        this.point2=new Point(point2);
    }

    // délégation de la reconnaissance de terrain

    @Override
    public Point getPosition() {
        return point1;
    }

    @Override
    public Point getSecondPosition() {
        return point2;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean interact(Element element, Level level) {
        Lemming l = (Lemming) element;

        if (l.isAt(point1)){
            l.setPosition(point2);
        }
        else if (l.isAt(point2)){
            l.setPosition(point1);
        }
        return true;
    }

    @Override
    public String getType() {
        return "teleporter";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.TELEPORTER;
    }
}
