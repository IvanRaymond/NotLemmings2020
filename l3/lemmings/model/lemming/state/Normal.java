package l3.lemmings.model.lemming.state;

import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.lemming.direction.Direction;

public class Normal implements State {

    Lemming lemming;
    Direction direction;

    public Normal(Lemming lemming) {
        this.lemming = lemming;
        direction = lemming.getDirection();
    }

    @Override
    public String toString() {
        return "State Normal";
    }

    @Override
    public boolean doAction() {
        return false;
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
        } else {
            direction.climb();
        }
        return true;
    }

    @Override
    public boolean fallingLow() {
        direction.stop();
        direction.fall();
        lemming.getStats().fallFast();
        return true;
    }

    @Override
    public boolean fallingHigh() {
        direction.stop();
        direction.fall();
        lemming.getStats().fallFast();
        lemming.getStats().toKill();
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.NORMAL;
    }
}