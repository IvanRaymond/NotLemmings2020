package l3.lemmings.observable.lemming.state;

/**
 * Defines the Lemming behaviour when the game physics detects a surrounding change
 */
public interface State {

    boolean walk();

    boolean reachWall(boolean isBlockOnTop);

    boolean reachBlock(boolean isBlockOnTop);

    boolean fallingLow();

    boolean fallingHigh();

    boolean isState(Activity state);

}
