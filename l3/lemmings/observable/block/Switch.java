package l3.lemmings.observable.block;

import l3.lemmings.observable.IDrawable;
import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observer.IObserver;

import javax.swing.text.Element;
import java.awt.*;
import java.util.ArrayList;

public class Switch implements IElement {

    private final Point point = new Point();
    private ArrayList<IElement> blocks;
    private Level level;
    private IDrawable drawable;

    public Switch(int x, int y, ArrayList<IElement> b, Level level) {
        point.x = x;
        point.y = y;
        blocks = b;
        this.level = level;
        drawable = new SwitchDrawable(this);
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
        level.getElements().addAll(blocks);
        level.removeElement(point);
        return true;
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }

    @Override
    public IDrawable view() {
        return drawable;
    }

}
