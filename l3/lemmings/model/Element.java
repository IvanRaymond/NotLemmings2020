package l3.lemmings.model;

import java.awt.*;

/**
 * Element abstract class
 * When adding a new element type, add type to Type enum to define compare method
 */
public interface Element {

    Point getPosition();

    /**
     * Getter for second position of an object
     *
     * @return Position if it exists or null
     */
    Point getSecondPosition();

    boolean update();

    /**
     * Defines the interaction with a non lemming object
     *
     * @param element The caller of the interaction
     * @param level   The context
     * @return true if the object can be interacted with
     */
    boolean interact(Element element, Level level);

    String getType();

    boolean compare(Type type);
}
