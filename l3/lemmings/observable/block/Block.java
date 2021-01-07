package l3.lemmings.observable.block;

import l3.lemmings.observable.IDrawable;
import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;

import java.awt.*;

public class Block implements IElement {

    private final Point point = new Point();
    private Level level;

    public Block(int x, int y, Level level) {
        point.x = x;
        point.y = y;
        this.level = level;
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
    public boolean compare(Type type) {
        return type == Type.CLIMBABLE;
    }

    @Override
    public boolean destroy() {
        level.removeElement(point);
        return true;
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }

    public void setY(int y) {
        point.y = y;
    }
}
