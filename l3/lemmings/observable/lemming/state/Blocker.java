package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.IObserver;

public class Blocker implements State, IObserver {

    LemmingObservable lemming;
    Game game;

    public Blocker(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
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
        return state == Activity.BLOCKER;
    }

    @Override
    public void update() {
        for(int i=0; i<game.getLevel().getLemmings().size(); i++){
            if(game.getLevel().getLemmings().get(i).isAt(lemming.getPosition())){
                game.getLevel().getLemmings().get(i).getDirection().turnAround();
            }
        }
    }
}
