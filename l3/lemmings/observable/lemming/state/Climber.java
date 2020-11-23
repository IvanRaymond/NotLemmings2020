package l3.lemmings.observable.lemming.state;

import l3.lemmings.observable.Game;
import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;

public class Climber implements State {

    LemmingObservable lemming;
    Game game;
    boolean finished = false;
    boolean isBlockOnTop;

    public Climber(LemmingObservable lemming, Game context) {
        this.lemming = lemming;
        game = context;
    }

    @Override
    public String toString() {
        return "State Climber";
    }

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public boolean walk() {
        if(lemming.getDirection().isClimbing() && finished){
            lemming.getDirection().level();
            lemming.setState(new Normal(lemming));
        }
        return new Normal(lemming).walk();
    }

    @Override
    public boolean reachWall(boolean isBlockOnTop) {
        if(isBlockOnTop){
            lemming.setState(new Normal(lemming));
        }
        lemming.getDirection().stop();
        lemming.getDirection().climb();
        this.isBlockOnTop=isBlockOnTop;
        return true;
    }

    @Override
    public boolean reachBlock(boolean isBlockOnTop) {

        if(lemming.getDirection().isClimbing() && !finished){
            if(isBlockOnTop){
                lemming.getDirection().fall();
                lemming.setState(new Normal(lemming));
            }else {
                lemming.getDirection().march();
            }
            finished = true;
            return true;
        }

        return new Normal(lemming).reachBlock(isBlockOnTop);
    }

    @Override
    public boolean fallingLow() {
        if (!lemming.getDirection().isClimbing()) {
            new Normal(lemming).fallingLow();
        }else if(lemming.surrounding().isBlockOnTop()){     // Might need to be replace for something less dependent on surrounding
            lemming.getDirection().fall();
            lemming.getDirection().march();
            lemming.getDirection().turnAround();
            lemming.getDirection().stop();
            lemming.setState(new Normal(lemming));
        }
        return true;
    }

    @Override
    public boolean fallingHigh() {
        if (!lemming.getDirection().isClimbing()) {
            new Normal(lemming).fallingHigh();
        } else if(lemming.surrounding().isBlockOnTop()){
            lemming.getDirection().fall();
            lemming.getStats().toKill();
            lemming.setState(new Normal(lemming));
        }
        return true;
    }

    @Override
    public boolean isState(Activity state) {
        return state == Activity.CLIMBER;
    }
}
