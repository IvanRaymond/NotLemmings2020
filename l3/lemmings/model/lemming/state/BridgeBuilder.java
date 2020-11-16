package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.Lemming;

public class BridgeBuilder implements State, Behaviour {

    Lemming lemming;
    Game game;

    public BridgeBuilder(Lemming lemming, Game context){
        this.lemming = lemming;
        game = context;
    }

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public boolean walk() {
        return false;
    }

    @Override
    public boolean reachWall() {
        return false;
    }

    @Override
    public boolean reachBlock() {
        return false;
    }

    @Override
    public boolean fallingLow() {
        return false;
    }

    @Override
    public boolean fallingHigh() {
        return false;
    }
}
