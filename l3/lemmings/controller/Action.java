package l3.lemmings.controller;

import l3.lemmings.controller.UIcontroller.Button;
import l3.lemmings.model.Game;
import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.lemming.state.*;

public class Action {

    private final Button clicked;
    private final Listener l;
    private final Game game;

    public Action(Button clicked, Listener l, Game game) {
        this.clicked = clicked;
        this.l = l;
        this.game = game;
    }

    public void doAction() {
        if (!gameAction(clicked)) {
            l.setActionFlag();
        }
    }

    // Expects false return to set actionFlag faster (Change to true if want to keep action "armed")
    public boolean setAction(Lemming lemming) {
        switch (clicked) {
            case CLIMBER:
                lemming.setState(new Climber(lemming, game));
                return true;

            case FLOATER:
                lemming.setState(new Floater(lemming, game));
                return true;

            case BOMB:
                lemming.setState(new Bomber(lemming, game));
                return true;

            case BLOCKER:
                lemming.setState(new Blocker(lemming, game));
                return true;

            case BRIDGE_BUILDER:
                lemming.setState(new BridgeBuilder(lemming, game));
                return true;

            case BASHER:
                lemming.setState(new Basher(lemming, game));
                return true;

            case MINER:
                lemming.setState(new Normal(lemming));
                return true;

            case DIGGER:
                lemming.setState(new Digger(lemming, game));
                return true;
        }
        return false;
    }

    private boolean gameAction(Button clicked) {
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

    private boolean decrease() {
        game.getLevel().decreaseFlow();
        game.getLevel().printFlow();
        return true;
    }

    private boolean increase() {
        game.getLevel().increaseFlow();
        game.getLevel().printFlow();
        return true;
    }

    private boolean pause() {
        game.togglePause();
        return true;
    }

    private boolean nuke() {
        game.getLevel().killAll();
        return true;
    }

}
