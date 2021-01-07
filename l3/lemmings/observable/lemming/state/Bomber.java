package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

import java.awt.*;

public class Bomber implements State {

    LemmingObservable lemming;
    Game game;

    public Bomber(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    private void bomb() {
        lemming.getDirection().stop();
        game.getLevel().destroySurrounding(lemming.getPosition(), 2);
        lemming.getStats().kill();
    }

    @Override
    public boolean walk() {
        bomb();
        return false;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        bomb();
        return false;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        bomb();
        return false;
    }

    @Override
    public boolean fallingLow() {
        bomb();
        return false;
    }

    @Override
    public boolean fallingHigh() {
        bomb();
        return false;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.BOMBER;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }
}
