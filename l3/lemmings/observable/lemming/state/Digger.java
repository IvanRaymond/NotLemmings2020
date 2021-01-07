package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

import java.awt.*;

public class Digger implements State {

    LemmingObservable lemming;
    Game game;

    int digCount = 0;
    private static final int DIGCOUNT = 5;

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
    public boolean walk() {
        if(digCount >= DIGCOUNT){
            lemming.setState(new Normal(lemming));
            return true;
        }else {
            lemming.getDirection().stop();
            Point toBreak = new Point(lemming.getPosition().x, lemming.getPosition().y + 1);
            //ToDo: Call destroy method rather than remove
//            game.getLevel().removeElement(toBreak);
            game.getLevel().getElement(toBreak).destroy();
            incDigCount();
        }
        return true;
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
        return new Normal(lemming).fallingLow();
    }

    @Override
    public boolean fallingHigh() {
        return new Normal(lemming).fallingHigh();
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.DIGGER;
    }

    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }
}
