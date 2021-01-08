package l3.lemmings.observable;

import java.awt.*;

/**
 * Element abstract class
 */
public interface IElement {

    Point getPosition();

    /**
     * Getter for second position of an object
     * @return Position if it exists or null
     */
    Point getSecondPosition();

    boolean compare(Type type);

    boolean destroy();

    Color getColor();

    IDrawable view();
}
