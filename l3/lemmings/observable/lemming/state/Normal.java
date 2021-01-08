package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observable.lemming.direction.Direction;

import java.awt.*;

public class Normal implements State {

    private LemmingObservable lemming;
    private Direction direction;

    public Normal(LemmingObservable lemming) {
        this.lemming = lemming;
        direction = lemming.getDirection();
    }

    @Override
    public String toString() {
        return "State Normal";
    }

    @Override
    public boolean walk() {
        if (lemming.getStats().toKillVal()) {
            lemming.getStats().kill();
        }
        direction.level();
        if (direction.isStill()) {
            direction.march();
        }
        return true;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        direction.turnAround();
        return true;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        if (isBlockOnTop) {
            direction.turnAround();
            return false;
        } else {
            direction.climb();
            return true;
        }
    }

    private void fall(){
        direction.stop();
        direction.fall();
        lemming.getStats().fallFast();
    }

    @Override
    public boolean fallingLow() {
        fall();
        return true;
    }

    @Override
    public boolean fallingHigh() {
        fall();
        lemming.getStats().toKill();
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.NORMAL;
    }

    @Override
    public Color getColor() {
        return Color.lightGray;
    }
}