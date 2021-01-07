package l3.lemmings.observable.trap;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;

import java.awt.*;

public class Lava implements IElement, IObserver {

    private final Point point = new Point();
    private Level level;

    public Lava(int x, int y) {
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
    public void update() {
        for(int i = 0; i < level.getLemmings().size(); i++){
            LemmingObservable l = (LemmingObservable) level.getLemmings().get(i);
            if (l.isAt(point) || l.isAt(new Point(point.x, point.y - 1))) {
                l.getStats().kill();
                l.notifyObservers();
            }
        }
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.LAVA;
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

}
