package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

import java.awt.*;

public class Basher implements State {

    LemmingObservable lemming;
    Game game;
    boolean digging = false;

    public Basher(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    @Override
    public boolean walk() {
        if(!lemming.surrounding().isBlockAhead() && digging == true){
            lemming.setState(new Normal(lemming));
        }
        return true;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        Point toBreak = new Point(lemming.getPosition().x + lemming.getDirection().getIntX() , lemming.getPosition().y);
        game.getLevel().getElement(toBreak).destroy();
        digging = true;
        return true;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        Point toBreak = new Point(lemming.getPosition().x + lemming.getDirection().getIntX() , lemming.getPosition().y);
        game.getLevel().getElement(toBreak).destroy();
        digging = true;
        return true;
    }

    @Override
    public boolean fallingLow() {
        lemming.setState(new Normal(lemming));
        return new Normal(lemming).fallingLow();
    }

    @Override
    public boolean fallingHigh() {
        lemming.setState(new Normal(lemming));
        return new Normal(lemming).fallingHigh();
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.BASHER;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
