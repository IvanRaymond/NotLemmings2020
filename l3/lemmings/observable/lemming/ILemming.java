package l3.lemmings.observable.lemming;

import l3.lemmings.observable.Type;
import l3.lemmings.observable.lemming.direction.Direction;

import java.awt.*;

public interface ILemming {

    public Point getPosition();
    public Direction getDirection();
    public void move();
    public boolean isAlive();
    public boolean compare(Type type);

}
