package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observable.lemming.direction.Direction;

import java.awt.*;

public class Floater implements State {

    private LemmingObservable lemming;
    private Game game;
    private Direction direction;
    private boolean fellHigh = false;

    public Floater(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        direction = lemming.getDirection();
        game = context;
    }

    @Override
    public String toString() {
        return "State Floater";
    }

    @Override
    public boolean walk() {
        direction.level();
        if (direction.isStill()) {
            direction.march();
        }
        if (fellHigh){
            lemming.setState(new Normal(lemming));
        }
        return true;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        return new Normal(lemming).reachWall(isBlockOnTop);
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        return new Normal(lemming).reachBlock(isBlockOnTop);
    }

    @Override
    public boolean fallingLow() {
        return new Normal(lemming).fallingLow();
    }

    @Override
    public boolean fallingHigh() {
        direction.stop();
        direction.fall();
        fellHigh = true;
        lemming.getStats().fallSlow();
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.FLOATER;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}
