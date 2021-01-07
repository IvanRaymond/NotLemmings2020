package l3.lemmings.observable.block;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observer.IObserver;

import java.awt.*;

public class Bomb implements IElement {

    private final Point point = new Point();
    private Level level;

    public Bomb(int x, int y, Level level) {
        point.x = x;
        point.y = y;
        this.level = level;
    }

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
    public boolean compare(Type type) {
        return type == Type.CLIMBABLE;
    }

    @Override
    public boolean destroy() {
        level.killSurrounding(point, 2);
        level.removeElement(point);
        return true;
    }

    @Override
    public Color getColor() {
        return Color.lightGray;
    }
}
