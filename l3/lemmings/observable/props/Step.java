package l3.lemmings.observable.props;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Type;

import java.awt.*;

public class Step implements IElement {
    private Point point = new Point();

    public Step(int x, int y) {
        point.x = x;
        point.y = y;
    }

    public Step(Point point){
        this.point = point;
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
        return true;
    }

    @Override
    public Color getColor() {
        return Color.yellow;
    }

}
