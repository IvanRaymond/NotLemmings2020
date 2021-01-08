package l3.lemmings.observable;

import l3.lemmings.observable.lemming.Lemming;
import l3.lemmings.observable.lemming.LemmingObservable;
import l3.lemmings.observable.lemming.direction.DirHorizontal;
import l3.lemmings.observable.lemming.direction.DirVertical;

import java.awt.*;

/**
 * Use the surrounding of a lemming to call methods of behaviour defined in the lemming's state
 */
public class Physics {

    private Level level;

    public Physics(Level level) {
        this.level = level;
    }

    public void step() {

        for (int i = 0; i< level.getLemmings().size(); i++) {

            LemmingObservable lemmingObservable = level.getLemmings().get(i);
            Lemming l = lemmingObservable.getLemming();

//            l.move();
            l.surrounding().update();
//                System.out.println(l.state().toString());
//                System.out.println("alive = "+l.getStats().alive());

            if (l.surrounding().isFloor()) {
                l.state().walk();
            }

            // Special case for climber
            if (l.getDirection().isClimbing() && l.surrounding().isBlockSides()){
                l.state().reachBlock(l.surrounding().isBlockOnTop());
            }

            if (l.surrounding().isBlockAhead()) {
                l.state().reachBlock(l.surrounding().isBlockOnTop());
            }

            if (l.surrounding().isWallAhead()) {
                l.state().reachWall(l.surrounding().isBlockOnTop());
            }

            if (l.surrounding().isPitLow()) {
                l.state().fallingLow();
            }

            if (l.surrounding().isPitHigh()) {
                l.state().fallingHigh();
            }

            if (!l.getStats().alive()){
                level.removeLemming(l);
            }
        }
    }
}
