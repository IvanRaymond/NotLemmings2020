package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

import java.awt.*;

public class BridgeBuilder implements State {

    LemmingObservable lemming;
    Game game;
    static final int STEPCOUNT = 5;

    public BridgeBuilder(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    int count = 0;
    boolean building = false;
    private void build(){
        if(count == STEPCOUNT){
            lemming.getDirection().level();
            lemming.setState(new Normal(lemming));
        }
        else if (!building) {
            game.getLevel().buildStaircase(lemming);
        }
    }

    @Override
    public boolean walk() {
        build();
        return true;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        return new Normal(lemming).reachWall(isBlockOnTop);
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        if(new Normal(lemming).reachBlock(isBlockOnTop)){
            build();
        }
        return true;
    }

    @Override
    public boolean fallingLow() {
        return new Normal(lemming).fallingLow();
    }

    @Override
    public boolean fallingHigh() {
        return new Normal(lemming).fallingHigh();
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.BRIDGE_BUILDER;
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
