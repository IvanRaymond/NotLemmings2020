package l3.lemmings.controller;

import l3.lemmings.controller.UIcontroller.Button;
import l3.lemmings.model.Lemming.LemmingState;
import l3.lemmings.model.Lemming;

public class Action {

    Button clicked;
    Listener l;

    public Action(Button clicked, Listener l){
        this.clicked = clicked;
        this.l = l;
    }

    public void doAction(){
        if (immediateAction(clicked) == false) {
            l.setActionCall();
        }
    }

    // Expects false return to set actionFlag faster
    public boolean setAction(Lemming lemming){
        switch (clicked) {
            case CLIMBER:
                lemming.setState(LemmingState.CLIMBER);
                return false;

            case FLOATER:
                lemming.setState(LemmingState.FLOATER);
                return false;

            case BOMB:
                lemming.setState(LemmingState.BOMB);
                return false;

            case BLOCKER:
                lemming.setState(LemmingState.BLOCKER);
                return false;

            case BRIDGE_BUILDER:
                lemming.setState(LemmingState.BRIDGE_BUILDER);
                return false;

            case BASHER:
                lemming.setState(LemmingState.BASHER);
                return false;

            case MINER:
                lemming.setState(LemmingState.MINER);
                return false;

            case DIGGER:
                lemming.setState(LemmingState.DIGGER);
                return false;
        }
        return true;
    }

    private boolean immediateAction(Button clicked){
        switch (clicked) {
            case DECREASE:
                return decrease();

            case INCREASE:
                return increase();

            case PAUSE:
                return pause();

            case NUKE:
                return nuke();
        }
        return false;
    }

    private boolean decrease(){

        return true;
    }

    private boolean increase(){

        return true;
    }

    private boolean pause(){

        return true;
    }

    private boolean nuke(){

        return true;
    }

}
