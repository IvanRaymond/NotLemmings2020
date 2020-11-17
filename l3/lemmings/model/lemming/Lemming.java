package l3.lemmings.model.lemming;

import l3.lemmings.model.Element;
import l3.lemmings.model.Level;
import l3.lemmings.model.Type;
import l3.lemmings.model.lemming.direction.DirHorizontal;
import l3.lemmings.model.lemming.direction.DirVertical;
import l3.lemmings.model.lemming.direction.Direction;
import l3.lemmings.model.lemming.state.Normal;
import l3.lemmings.model.lemming.state.State;

import java.awt.*;

import static l3.lemmings.model.lemming.direction.DirHorizontal.RIGHT;
import static l3.lemmings.model.lemming.direction.DirVertical.STILL;

public class Lemming implements Element {


    private State state;
    private final Direction direction;
    private final Stats stats;
    private final Surrounding surrounding;

    private Point position = new Point();

    public Lemming(Level level, int x, int y) {
        position.x = x;
        position.y = y;
        direction = new Direction(RIGHT, STILL);
        state = new Normal(this);
        stats = new Stats();
        surrounding = new Surrounding(level.getElements(), this);
    }

    public void setState(State state) {
        this.state = state;
    }

//	public void incFallCount(){
//		fallCount++;
//	}
//	public void resetFallCount(){
//		fallCount=0;
//	}

    public Surrounding surrounding() {
        return surrounding;
    }

    public State state() {
        return state;
    }

    public boolean doAction() {
        return state.doAction();
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
        return point.equals(point);
    }

    @Override
    public Point getPosition() {
        return new Point(position);
    }

    @Override
    public Point getSecondPosition() {
        return null;
    }

    @Override
    public boolean update() {

        state.doAction();

//        if (getDirection().isGoing(DirHorizontal.RIGHT)) {
//            setPosition(new Point(getX() + 1, getY()));
//        }
//        if (getDirection().isGoing(DirHorizontal.LEFT)) {
//            setPosition(new Point(getX() - 1, getY()));
//        }
//        if (getDirection().isGoing(DirVertical.UP)) {
//            setPosition(new Point(getX(), getY() - 1));
//        }
//        if (getDirection().isGoing(DirVertical.DOWN)) {
//            setPosition(new Point(getX(), getY() + 1));
//        }
        return true;
    }

    @Override
    public boolean interact(Element element, Level level) {
        return false;
    }

    @Override
    public String getType() {
        return "lemming";
    }

    @Override
    public boolean compare(Type type) {
        return type == Type.LEMMING;
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
}
