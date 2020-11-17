package l3.lemmings.model.props;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;
import l3.lemmings.model.lemming.Lemming;

import java.awt.*;

public class Escape implements Element {

    private final Point point = new Point();

    public Escape(int x, int y) {
        point.x = x;
        point.y = y;
    }
//
//    public void reach(Level level){
//        ArrayList<Lemming> lemmings = level.getLemmings();
//        for (Lemming lemming : lemmings){
//            if (lemming.getX() == x && lemming.getY() == y){
//                lemming.kill();
//                level.incSafe();
//            }
//        }
//        level.setLemmings(lemmings);
//    }


    public int getX() {
        return point.x;
    }

    public int getY() {
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
    public boolean update() {


        return false;
    }

    @Override
    public boolean interact(Element element, Level level) {
        Lemming l = (Lemming) element;
        if (l.isAt(point)) {
            l.getStats().kill();
            level.incSafe();
        }
        return true;
    }

    @Override
    public String getType() {
        return "escape";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.ESCAPE;
    }
}
