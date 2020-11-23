package l3.lemmings.observable.block;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observer.IObserver;

import java.awt.*;

public class Bomb implements IElement, IObserver {

    private final Point point = new Point();

    public Bomb(int x, int y) {
        point.x = x;
        point.y = y;
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
    public void update() {

    }

    @Override
    public boolean compare(Type type) {
        return type == Type.BOMB;
    }
}
