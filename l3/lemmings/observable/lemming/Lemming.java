package l3.lemmings.observable.lemming;

import l3.lemmings.observable.IElement;
import l3.lemmings.observable.Level;
import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.direction.DirHorizontal;
import l3.lemmings.observable.lemming.direction.DirVertical;
import l3.lemmings.observable.lemming.direction.Direction;
import l3.lemmings.observable.lemming.state.State;

import java.awt.*;

import static l3.lemmings.observable.lemming.direction.DirHorizontal.RIGHT;
import static l3.lemmings.observable.lemming.direction.DirVertical.STILL;

public class Lemming implements ILemming, IElement {


    private State state;
    private final Direction direction;
    private final Stats stats;
    private final Surrounding surrounding;

    private Point position = new Point();

    private int count = 0;

    public Lemming(Level level, int x, int y) {
        position.x = x;
        position.y = y;
        direction = new Direction(RIGHT, STILL);
        stats = new Stats();
        surrounding = new Surrounding(level.getElements(), this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public Surrounding surrounding() {
        return surrounding;
    }

    public State state() {
        return state;
    }

    public Stats getStats() {
        return stats;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public boolean isAt(Point point) {
        return position.getX() == point.getX() && position.getY() == point.getY();
    }

    @Override
    public Point getPosition() {
        return new Point(position);
    }

    @Override
    public Point getSecondPosition() {
        return null;
    }

    public void setX(int x) {
        position.x = x;
    }

    public void setY(int y) {
        position.y = y;
    }

    public void setPosition(Point position) {
        this.position = new Point(position);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move() {
        if(getStats().isFallingFast()) {
            if (getDirection().isGoing(DirHorizontal.RIGHT)) {
                setPosition(new Point(getX() + 1, getY()));
            }
            if (getDirection().isGoing(DirHorizontal.LEFT)) {
                setPosition(new Point(getX() - 1, getY()));
            }
            if (getDirection().isGoing(DirVertical.UP)) {
                setPosition(new Point(getX(), getY() - 1));
            }
            if (getDirection().isGoing(DirVertical.DOWN)) {
                setPosition(new Point(getX(), getY() + 1));
            }
        }else if(count>2){ // Why did i put a counter there...
            if (getDirection().isGoing(DirVertical.DOWN)) {
                setPosition(new Point(getX(), getY() + 1));
            }
            count = 0;
        }
        count++;
    }

    @Override
    public boolean isAlive() {
        return getStats().alive();
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.LEMMING;
    }

    @Override
    public boolean destroy() {
        return false;
    }

    @Override
    public Color getColor() {
        return state.getColor();
    }
}
