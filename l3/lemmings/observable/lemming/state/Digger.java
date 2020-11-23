package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

public class Digger implements State {

    LemmingObservable lemming;
    Game game;

    int digCount = 0;

    public Digger(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    public void incDigCount() {
        digCount++;
    }

    public int getDigCount() {
        return digCount;
    }

    public void resetDigCount() {
        digCount = 0;
    }

    @Override
    public boolean doAction() {


        return true;
    }

    @Override
    public boolean walk() {
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
        return state == Activity.DIGGER;
    }
}
