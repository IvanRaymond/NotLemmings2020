package l3.lemmings.model;

import l3.lemmings.model.lemming.Lemming;
import l3.lemmings.model.lemming.direction.DirHorizontal;
import l3.lemmings.model.lemming.direction.DirVertical;

import java.awt.*;

/**
 * Uses the lemming's Surrounding and State methods to apply game physics
 */
public class Physics {

    Level level;

    public Physics(Level level) {
        this.level = level;
    }

    public void update() {

        for (int i = 0; i< level.getElements().size(); i++) {

            Element e = level.getElements().get(i);

            if (e.compare(Type.LEMMING)) {
                Lemming l = (Lemming) e;

                move(l);
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
                    level.remove(l);
                }
            }
        }
    }

    // Peut etre utiliser à la place de e.update en cas de prob de perf
    private void move(Lemming l) {
        if (l.getDirection().isGoing(DirHorizontal.RIGHT)) {
            l.setPosition(new Point(l.getX() + 1, l.getY()));
        }
        if (l.getDirection().isGoing(DirHorizontal.LEFT)) {
            l.setPosition(new Point(l.getX() - 1, l.getY()));
        }
        if (l.getDirection().isGoing(DirVertical.UP)) {
            l.setPosition(new Point(l.getX(), l.getY() - 1));
        }
        if (l.getDirection().isGoing(DirVertical.DOWN)) {
            l.setPosition(new Point(l.getX(), l.getY() + 1));
        }
    }
}
