package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.direction.Direction;
import l3.lemmings.model.lemming.Lemming;

public class Floater implements State, Behaviour {

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
        return true;
    }

    @Override
    public boolean reachWall() {
        direction.turnAround();
        return true;
    }

    @Override
    public boolean reachBlock() {
        direction.climb();
        return true;
    }

    @Override
    public boolean fallingLow() {
        direction.fall();
        return true;
    }

    @Override
    public boolean fallingHigh() {
        direction.fall();
        lemming.getStats().fallSlow();
        return true;
    }
}
