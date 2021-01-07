package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;

import java.awt.*;

public class Blocker implements State, IObserver {

    LemmingObservable lemming;
    Game game;

    public Blocker(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    private void stop(){
        lemming.getDirection().level();
        lemming.getDirection().stop();
    }

    @Override
    public boolean walk() {
        stop();
        return false;
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        stop();
        return false;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {
        stop();
        return false;
    }

    @Override
    public boolean fallingLow() {
        new Normal(lemming).fallingLow();
        return false;
    }

    @Override
    public boolean fallingHigh() {
        new Normal(lemming).fallingHigh();
        return false;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.BLOCKER;
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public void update() {
        for(int i=0; i<game.getLevel().getLemmings().size(); i++){
            if(game.getLevel().getLemmings().get(i).isAt(
                    new Point(lemming.getPosition().x+1, lemming.getPosition().y)) ||
                    game.getLevel().getLemmings().get(i).isAt(
                    new Point(lemming.getPosition().x-1, lemming.getPosition().y))
            ){
                game.getLevel().getLemmings().get(i).getDirection().turnAround();
            }
        }
    }
}
