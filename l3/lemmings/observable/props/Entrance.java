package l3.lemmings.observable.props;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;

import java.awt.*;

/**
 * Represents the point of creation of lemmings
 */
public class Entrance implements IElement {

    private final Point point = new Point();
    private final Level level;
    private int numberOfLemmings;
    private boolean done = false;
    int n;

    public Entrance(Level level, int numberOfLemmings, int x, int y) {
        point.x = x;
        point.y = y;
        this.level = level;
        this.numberOfLemmings = numberOfLemmings;
        n = numberOfLemmings;
    }

    public void addLemming() {
        if (numberOfLemmings > 0) {
            level.addLemmings(new LemmingObservable(level, point.x, point.y));
            numberOfLemmings--;
        } else {
            done = true;
        }
    }

    public void printNumberOfLemmings() {
        System.out.println("" + n);
    }

    public boolean getDone() {
        return done;
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
        return type == Type.ENTRANCE;
    }
}
