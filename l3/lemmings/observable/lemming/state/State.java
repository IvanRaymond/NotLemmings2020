package l3.lemmings.observable.lemming.state;

import java.awt.*;

/**
 * Defines the Lemming's behaviour
 */
public interface State {

    boolean walk();

    boolean reachWall(boolean isBlockOnTop);

    boolean reachBlock(boolean isBlockOnTop);

    boolean fallingLow();

    boolean fallingHigh();

    boolean isState(Activity state);

    Color getColor();

}
