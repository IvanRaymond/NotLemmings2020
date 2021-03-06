package l3.lemmings.observer;

import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observer.UIcontroller.Button;
import l3.lemmings.observable.Game;
import l3.lemmings.observable.Level;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Treats mouse inputs and decide on the action to make.
 *
 * @author Ivan
 */
public class Listener extends MouseAdapter {

    private final Views view;
    private final Level level;
    private final UIcontroller uiController;
    private boolean actionFlag = false;
    private Action action;
    private final Game game;
    private int size;

    public Listener(Views view, Game game) {
        this.level = game.getLevel();
        this.view = view;
        this.game = game;
        uiController = new UIcontroller(view);
        size = level.getLemmings().size();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point cell = uiController.pointToMatrix(e.getPoint());
            if (uiController.isButton(cell)) {
                Button clicked = uiController.getButton(cell);
                action = new Action(clicked, this, game);
                action.doAction();
                System.out.println(clicked.toString());
            }
            if (uiController.isCellPlayable(cell)) {
                if (level.lemmingPresent(cell) && actionFlag) {
                    actionFlag = action.setAction(level.getLemming(cell));
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    public void setActionFlag() {
        actionFlag = true;
    }

    public void registerView(LemmingObservable l){
        l.register(view);
    }

}
