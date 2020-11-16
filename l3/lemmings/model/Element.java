package l3.lemmings.model;

import java.awt.*;

/**
 * Element abstract class
 * When adding a new element type, add type to Type enum to define compare method
 */
public interface Element {

    public Point getPosition();

    /**
     * Getter for second position of an object
     * @return Position if it exists or null
     */
    public Point getSecondPosition();

    public boolean move();

    /**
     * Defines the interaction with a non lemming object
     * @param element The caller of the interaction
     * @param level The context
     * @return true if the object can be interacted with
     */
    public boolean interact(Element element, Level level);

    public String getType();

    public boolean compare(Type type);
}
