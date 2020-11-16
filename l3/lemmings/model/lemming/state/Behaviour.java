package l3.lemmings.model.lemming.state;

/**
 * Methods of Behaviour are used to define behaviour of lemming for cases
 */
public interface Behaviour {

    public boolean walk();
    public boolean reachWall();
    public boolean reachBlock();
    public boolean fallingLow();
    public boolean fallingHigh();

}
