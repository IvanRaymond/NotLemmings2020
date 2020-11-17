package l3.lemmings.model.lemming.state;

import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.Lemming;

public class Digger implements State {

    Lemming lemming;
    Game game;

    int digCount = 0;

    public Digger(Lemming lemming, Game context) {
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
