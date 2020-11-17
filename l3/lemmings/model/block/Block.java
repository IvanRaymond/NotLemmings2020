package l3.lemmings.model.block;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;

import java.awt.*;

public class Block implements Element {

    private final Point point = new Point();

    public Block(int x, int y) {
        point.x = x;
        point.y = y;
    }

    public int getX() {
        return point.x;
    }

    public void setX(int x) {
        point.x = x;
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
        return false;
    }

    @Override
    public String getType() {
        return "block";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.BLOCK;
    }

    public void setY(int y) {
        point.y = y;
    }

}
