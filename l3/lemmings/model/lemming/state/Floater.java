package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.direction.Direction;
import l3.lemmings.model.lemming.Lemming;

public class Floater implements State {

    Lemming lemming;
    Game game;
    Direction direction;

    public Floater(Lemming lemming, Game context){
        this.lemming = lemming;
        direction = lemming.getDirection();
        game = context;
    }

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public boolean walk() {
        direction.level();
        if(direction.isStill()){
            direction.march();
        }
        lemming.setState(new Normal(lemming));
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
        lemming.getStats().fallSlow();
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.FLOATER;
    }
}
