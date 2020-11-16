package l3.lemmings.model.lemming.state;

/**
 * State Interface that redefines the Lemming behaviour by his state
 */
public interface State {

    public boolean doAction();
    public boolean walk();
    public boolean reachWall(boolean isBlockOnTop);
    public boolean reachBlock(boolean isBlockOnTop);
    public boolean fallingLow();
    public boolean fallingHigh();
    public boolean isState(Activity state);

}
