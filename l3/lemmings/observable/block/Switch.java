package l3.lemmings.observable.block;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observer.IObserver;

import java.awt.*;
import java.util.ArrayList;

public class Switch implements IElement, IObserver {

    private final Point point = new Point();
    ArrayList<Block> blocks;

    public Switch(int x, int y, ArrayList<Block> b) {
        point.x = x;
        point.y = y;
        blocks = b;
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
        return type == Type.SWITCH;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}