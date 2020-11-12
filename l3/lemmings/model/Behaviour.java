package l3.lemmings.model;

import l3.lemmings.model.Game;
import l3.lemmings.model.Lemming;

/**
 * Todo Delete as this is implemented in games directly
 */
public class Behaviour {

    Game game;

    public Behaviour(Game game){
        this.game = game;
    }

    public void changeBehaviour(){
        for (Lemming lemming : game.getLevel().getLemmings()) {
            switch (lemming.getState()){
                case NORMAL:
                    setNormal();
                    break;
                case CLIMBER:
                    setClimber();
                    break;
                case FLOATER:
                    setFloater();
                    break;
                case BOMB:
                    setBomb();
                    break;
                case BLOCKER:
                    setBlocker();
                    break;
                case BRIDGE_BUILDER:
                    setBridge();
                    break;
                case BASHER:
                    setBasher();
                    break;
                case MINER:
                    setMiner();
                    break;
                case DIGGER:
                    setDigger();
                    break;
            }
        }
    }

    private void setDigger() {
    }

    private void setMiner() {
    }

    private void setBasher() {

    }

    private void setBridge() {
        // Add a bridge object to model, act as a series of blocks that are climbable
    }

    private void setBlocker() {
        // Lemming act as a two black tall structure, prevent lemmings from going further
    }

    private void setBomb() {

    }

    private void setFloater() {
        // Don't die if fall more than 5 blocks height
    }

    private void setClimber(){
        // While facing wall go up on y axis until reach top then do a setNormal
    }

    private void setNormal() {
        // Go forward until meet a two block tall structure. and die if fall more than 5 blocks height
    }

}
