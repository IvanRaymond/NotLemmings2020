package l3.lemmings.model.lemming.state;

import l3.lemmings.model.lemming.direction.Direction;
import l3.lemmings.model.lemming.Lemming;

public class Normal implements State {

    Lemming lemming;
    Direction direction;

    boolean kill = false;

    public Normal(Lemming lemming){
        this.lemming = lemming;
        direction = lemming.getDirection();
    }

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public boolean walk() {
        if(kill){
            lemming.getStats().kill();
        }
        direction.level();
        if(direction.isStill()){
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
        if(isBlockOnTop){
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
        return true;
    }

    @Override
    public boolean fallingHigh() {
        direction.stop();
        direction.fall();
        kill = true;
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.NORMAL;
    }
}