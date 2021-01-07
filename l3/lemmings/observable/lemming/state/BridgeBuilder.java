package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

import java.awt.*;

public class BridgeBuilder implements State {

    private LemmingObservable lemming;
    private Game game;
    static final int STEPCOUNT = 5;
    private int count = 0;

    public BridgeBuilder(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }


    private void build(){
        if(count == STEPCOUNT){
            lemming.getDirection().level();
            lemming.setState(new Normal(lemming));
        }
        else {
            game.getLevel().buildStep(new Point(lemming.getPosition().x+lemming.getDirection().getIntX(), lemming.getPosition().y));
            count++;
            lemming.getDirection().climb();
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
        build();
        return true;
    }

    @Override
    public boolean fallingHigh() {
        build();
        return true;
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
