package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.Lemming;

public class Bomber implements State {

    Lemming lemming;
    Game game;

    public Bomber(Lemming lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    @Override
    public boolean doAction() {
        game.getLevel().killSurrounding(lemming);
        lemming.getStats().kill();
        return false;
    }

    @Override
    public boolean walk() {
        lemming.getDirection().stop();
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
