package l3.lemmings.model.lemming.state;

/**
 * State Interface that redefines the Lemming behaviour by his state
 */
public interface State {

    boolean doAction();

    boolean walk();

    boolean reachWall(boolean isBlockOnTop);

    boolean reachBlock(boolean isBlockOnTop);

    boolean fallingLow();

    boolean fallingHigh();

    boolean isState(Activity state);

//    boolean finishedClimbing();

}
