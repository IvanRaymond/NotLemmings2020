package l3.lemmings.observable.props;

import l3.lemmings.observable.IDrawable;
import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;

import java.awt.*;

public class Teleporter implements IElement, IObserver {

    private Point point1 = new Point();
    private Point point2 = new Point();
    private Level level;
    private TeleporterDrawable drawable;

    public Teleporter(Level level, int x1, int y1, int x2, int y2) {
        point1.x = x1;
        point1.y = y1;
        point2.x = x2;
        point2.y = y2;
        this.level = level;
        drawable = new TeleporterDrawable(this);
    }

    public Teleporter(Point point1, Point point2) {
        this.point1 = new Point(point1);
        this.point2 = new Point(point2);
    }

    @Override
    public Point getPosition() {
        return point1;
    }

    @Override
    public Point getSecondPosition() {
        return point2;
    }

    @Override
    public void update() {
        for(int i = 0; i < level.getLemmings().size(); i++) {
            LemmingObservable l = (LemmingObservable) level.getLemmings().get(i);

            if (l.isAt(point1)) {
                l.setPosition(point2);
                l.notifyObservers();
            } else if (l.isAt(point2)) {
                l.setPosition(point1);
                l.notifyObservers();
            }
        }
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.TELEPORTER;
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    public Color getColor() {
        return Color.white;
    }

    @Override
    public IDrawable view() {
        return drawable;
    }
}
