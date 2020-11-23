package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

public class Bomber implements State {

    LemmingObservable lemming;
    Game game;

    public Bomber(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    @Override
    public boolean doAction() {

        return false;
    }

    @Override
    public boolean walk() {
        lemming.getDirection().stop();
        game.getLevel().killSurrounding(lemming);
        lemming.getStats().kill();
        return false;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        return false;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
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

    @Override
    public boolean isState(Activity state) {
        return state == Activity.BOMBER;
    }
}
