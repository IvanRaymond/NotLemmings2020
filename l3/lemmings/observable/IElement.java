package l3.lemmings.observable;

import java.awt.*;

/**
 * Element abstract class
 * When adding a new element type, add type to Type enum to define compare method
 */
public interface IElement {

    Point getPosition();
    /**
     * Getter for second position of an object
     *
     * @return Position if it exists or null
     */
    Point getSecondPosition();

    // ToDo: replace with comparable interface ???
    boolean compare(Type type);

    boolean isBreakable();

}
